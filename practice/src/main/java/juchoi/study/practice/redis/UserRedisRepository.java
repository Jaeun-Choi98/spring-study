package juchoi.study.practice.redis;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import juchoi.study.practice.redis.cache.UserCache;

public interface UserRedisRepository extends CrudRepository<UserCache, Long> {
  Optional<UserCache> findByEmail(String email);

  List<UserCache> findByIsActive(Boolean isActive);
}
