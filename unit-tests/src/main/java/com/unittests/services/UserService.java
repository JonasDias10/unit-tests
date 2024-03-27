package com.unittests.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unittests.exceptions.UserException;
import com.unittests.models.User;
import com.unittests.repositories.UserRepository;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

      public User save(User user) {
            return userRepository.save(user);
      }

      public User update(User user) {
            return userRepository.save(user);
      }

      public void delete(Long id) {
            if (!userRepository.existsById(id)) {
                  throw new UserException("User not found");
            }

            userRepository.deleteById(id);
      }

      public List<User> findAll() {
            return userRepository.findAll();
      }

      public User findByUsername(String username) {
            return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserException("User not found"));
      }
}
