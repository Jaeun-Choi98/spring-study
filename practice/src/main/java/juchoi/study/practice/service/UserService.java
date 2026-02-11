package juchoi.study.practice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import juchoi.study.practice.repogitory.UserRepository;
import juchoi.study.practice.repogitory.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  // 단일 조회
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  // 전체 조회
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // 조건 조회
  public List<User> serachUserByName(String name) {
    return userRepository.findByNameContaining(name);
  }

  // 페이징 조회
  public Page<User> getUserWithPaging(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());
    return userRepository.findAll(pageable);
  }

  // insert/update
  @Transactional
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  // 확장 insert
  @Transactional
  public List<User> saveAll(List<User> users) {
    return userRepository.saveAll(users);
  }

  // modify
  @Transactional
  public User updateUser(Long id, String name, String email) {
    User user = getUserById(id);
    user.setName(name);
    user.setEmail(email);
    return userRepository.save(user); // dirty checking으로 자동 update
  }

  // 삭제
  @Transactional
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  // 복잡한 조회 로직
  public List<User> getActiveUsersWithEmailDomain(String domain) {
    return userRepository.findByIsActive(true).stream()
        .filter(user -> user.getEmail().endsWith(domain))
        .collect(Collectors.toList());
  }
}
