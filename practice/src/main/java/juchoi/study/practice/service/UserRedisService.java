package juchoi.study.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import juchoi.study.practice.redis.UserRedisRepository;
import juchoi.study.practice.redis.cache.UserCache;
import juchoi.study.practice.repogitory.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRedisService {

  private final UserRedisRepository redisRepositroy;

  // 저장
  public void saveToCache(UserCache user) {
    // UserCache cache = UserCache.from(user);
    redisRepositroy.save(user);
    log.info("User cached: {}", user.getEmail());
  }

  // 조회 (Email)
  public Optional<UserCache> getByEmail(String email) {
    return redisRepositroy.findByEmail(email);
  }

  // 모든 캐시 조회
  public List<UserCache> getAllFromCache() {
    List<UserCache> result = new ArrayList<>();
    redisRepositroy.findAll().forEach(result::add);
    return result;
  }

}
