package com.example.multidatasources;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  @Transactional("client1TransactionManager")
  public List<User> index() {
    return userRepository.findAll();
  }

  @GetMapping("/usersTwo")
  @Transactional("client2TransactionManager")
  public List<User> indexTwo() {
    return userRepository.findAll();
  }
}
