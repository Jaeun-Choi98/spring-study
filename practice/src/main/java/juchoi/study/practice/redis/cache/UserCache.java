package juchoi.study.practice.redis.cache;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import juchoi.study.practice.repogitory.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash(value = "user", timeToLive = 3600)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCache {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Nullable
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Nullable
  private int createdAt;

  @Nullable
  private Boolean isActive;

  public static UserCache from(User user) {
    return UserCache.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .createdAt(user.getCreatedAt().getSecond())
        .isActive(user.getIsActive())
        .build();
  }

}
