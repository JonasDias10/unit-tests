package com.unittests.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.unittests.services.UserService;
import com.unittests.models.User;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

      @Autowired
      private UserService userService;

      @PostMapping("/")
      public User saveUser(@RequestBody User user) {
            return userService.save(user);
      }

      @DeleteMapping("/delete/{id}")
      public void deleteUser(@PathVariable Long id) {
            userService.delete(id);
      }

      @PutMapping("/update")
      public User updateUser(@RequestBody User user) {
            return userService.update(user);
      }

      @GetMapping("/")
      public List<User> findAllUsers() {
            return userService.findAll();
      }

      @GetMapping("/{username}")
      public User findUserByUsername(@PathVariable String username) {
            return userService.findByUsername(username);
      }

}
