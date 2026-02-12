package juchoi.study.practice;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import juchoi.study.practice.repogitory.UserRepository;
import juchoi.study.practice.repogitory.entity.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataConfig {

  private final UserRepository userRepository;

  @PostConstruct
  public void loadTestData() {
    if (userRepository.count() == 0) {
      userRepository.saveAll(List.of(
          new User(null, "A", "email A", null, true),
          new User(null, "B", "email B", null, true),
          new User(null, "C", "email C", null, true),
          new User(null, "D", "email D", null, true),
          new User(null, "E", "email E", null, true)));
      // User.builder().email("email A").name("A").isActive(true).build(),
      // User.builder().email("email B").name("B").isActive(true).build(),
      // User.builder().email("email C").name("C").isActive(true).build(),
      // User.builder().email("email D").name("D").isActive(true).build(),
      // User.builder().email("email E").name("E").isActive(true).build()));
    }
  }
}
