package juchoi.study.practice.redis;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import juchoi.study.practice.redis.cache.UserCache;
import juchoi.study.practice.repogitory.UserRepository;
import juchoi.study.practice.repogitory.entity.User;
import juchoi.study.practice.service.UserRedisTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

  private final UserRedisRepository redisRepositroy;
  private final UserRepository userRepository;

  private final UserRedisTemplateService userRedisTemplateService;

  @PostConstruct
  public void init() {

    log.info("레디스 초기화 시작");

    List<User> users = userRepository.findAll();
    for (User u : users) {
      redisRepositroy.save(UserCache.from(u));
      userRedisTemplateService.saveRedisUser("user" + u.getId(), UserCache.from(u));
    }
    log.info("레디스 초기화 완료");
  }
}
