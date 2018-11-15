package com.example.multidatasources;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<User> index(@RequestParam("clientName") String clientName) {
    ClientDataSourceSwitcher.setClientName(clientName);

    return userRepository.findAll();
  }
}
