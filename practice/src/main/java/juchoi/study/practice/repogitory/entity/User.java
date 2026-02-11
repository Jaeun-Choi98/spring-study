package juchoi.study.practice.repogitory.entity;

import java.time.LocalDateTime;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Nullable
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Nullable
  private LocalDateTime createdAt;

  @Nullable
  private Boolean isActive;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    if (isActive == null) {
      isActive = true;
    }
  }
}
