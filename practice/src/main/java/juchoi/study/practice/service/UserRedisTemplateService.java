package juchoi.study.practice.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import juchoi.study.practice.redis.cache.UserCache;
import juchoi.study.practice.repogitory.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRedisTemplateService {
  private final RedisTemplate<String, Object> redisTemplate;
  private final StringRedisTemplate stringRedisTemplate;
  private final ObjectMapper objectMapper = new ObjectMapper()
      .registerModule(new JavaTimeModule());

  // ==================== String 작업 ====================

  // 저장
  public void set(String key, String value) {
    stringRedisTemplate.opsForValue().set(key, value);
  }

  // 저장 (TTL 포함)
  public void setWithTTL(String key, String value, long timeout, TimeUnit unit) {
    stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
  }

  // 조회
  public String get(String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }

  // ==================== Object 작업 ====================

  // 객체 저장
  public void setObject(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  // 객체 저장 (TTL)
  public void setObjectWithTTL(String key, Object value, long timeout, TimeUnit unit) {
    redisTemplate.opsForValue().set(key, value, timeout, unit);
  }

  // 객체 조회
  public Object getObject(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  // ==================== User 작업 ====================

  // User 저장
  public void saveRedisUser(String key, UserCache user) {
    // UserDTO dto = UserDTO.from(user);
    redisTemplate.opsForValue().set(key, user, 1, TimeUnit.HOURS);
    log.info("User saved to Redis: {}", key);
  }

  // User 조회
  public UserCache getUser(String key) {
    Object obj = redisTemplate.opsForValue().get(key);
    if (obj instanceof UserCache) {
      return (UserCache) obj;
    }
    return null;
  }

  // ==================== Hash 작업 ====================

  // Hash 필드 저장
  public void hSet(String key, String field, Object value) {
    redisTemplate.opsForHash().put(key, field, value);
  }

  // Hash 필드 조회
  public Object hGet(String key, String field) {
    return redisTemplate.opsForHash().get(key, field);
  }

  // Hash 전체 조회
  public Map<Object, Object> hGetAll(String key) {
    return redisTemplate.opsForHash().entries(key);
  }

  // User를 Hash로 저장
  public void saveUserAsHash(String userId, User user) {
    Map<String, Object> userMap = new HashMap<>();
    userMap.put("id", user.getId());
    userMap.put("name", user.getName());
    userMap.put("email", user.getEmail());
    userMap.put("isActive", user.getIsActive());

    String key = "user:hash:" + userId;
    redisTemplate.opsForHash().putAll(key, userMap);
    redisTemplate.expire(key, 1, TimeUnit.HOURS);
  }

  // ==================== List 작업 ====================

  // List 왼쪽에 추가
  public void leftPush(String key, Object value) {
    redisTemplate.opsForList().leftPush(key, value);
  }

  // List 오른쪽에 추가
  public void rightPush(String key, Object value) {
    redisTemplate.opsForList().rightPush(key, value);
  }

  // List 범위 조회
  public List<Object> range(String key, long start, long end) {
    return redisTemplate.opsForList().range(key, start, end);
  }

  // ==================== Set 작업 ====================

  // Set에 추가
  public void addToSet(String key, Object... values) {
    redisTemplate.opsForSet().add(key, values);
  }

  // Set 멤버 조회
  public Set<Object> getSetMembers(String key) {
    return redisTemplate.opsForSet().members(key);
  }

  // ==================== Sorted Set 작업 ====================

  // Sorted Set에 추가
  public void addToZSet(String key, Object value, double score) {
    redisTemplate.opsForZSet().add(key, value, score);
  }

  // Sorted Set 범위 조회
  public Set<Object> rangeZSet(String key, long start, long end) {
    return redisTemplate.opsForZSet().range(key, start, end);
  }

  // ==================== 공통 작업 ====================

  // 삭제
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  // 여러 키 삭제
  public void deleteMultiple(Collection<String> keys) {
    redisTemplate.delete(keys);
  }

  // 존재 확인
  public boolean hasKey(String key) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(key));
  }

  // TTL 설정
  public void setExpire(String key, long timeout, TimeUnit unit) {
    redisTemplate.expire(key, timeout, unit);
  }

  // TTL 조회 (초 단위)
  public Long getExpire(String key) {
    return redisTemplate.getExpire(key);
  }

  // 패턴으로 키 찾기
  public Set<String> keys(String pattern) {
    return redisTemplate.keys(pattern);
  }
}
