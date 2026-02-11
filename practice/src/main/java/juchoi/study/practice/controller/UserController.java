package juchoi.study.practice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import juchoi.study.practice.repogitory.entity.User;
import juchoi.study.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public Iterable<User> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{email}")
  Optional<User> getUserByEmail(@PathVariable String email) {
    User user = userService.getUserByEmail(email);
    if (user != null) {
      return Optional.of(user);
    }
    return Optional.empty();
  }

  @PostMapping
  public User postUser(@RequestBody User user) {
    User insertUser = userService.saveUser(user);
    return insertUser;
  }

  @PatchMapping
  ResponseEntity<User> putUser(@PathVariable String email,
      @RequestBody User user) {
    User dbUser = userService.getUserByEmail(email);
    User updateUser = null;
    if (dbUser != null) {
      updateUser = userService.updateUser(dbUser.getId(), user.getName(), user.getEmail());
    }
    return (updateUser == null) ? new ResponseEntity<>(this.postUser(user), HttpStatus.CREATED)
        : new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping
  void deleteUser(@PathVariable String email) {
    User user = userService.getUserByEmail(email);
    if (user != null) {
      userService.deleteUser(user.getId());
    }
  }

}
