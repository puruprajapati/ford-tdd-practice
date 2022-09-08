package com.example.fordtdd2.ref;

import com.example.fordtdd.domain.User;
import com.example.fordtdd.repo.UserRepo;
import com.example.fordtdd.service.Impl.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
  @TestConfiguration
  static class UserServiceTestContextConfiguration{
    @Bean
    public UserService userService(){
      return new UserServiceImpl();
    }
  }

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepo userRepo;

  @BeforeEach
  public void setup(){
    User user = new User(1, "Ritesh Maharhan", "222222");
    Optional<User> userOptional = Optional.of(user);

    Mockito.when(userRepo.findById(1)).thenReturn(userOptional);
  }

  @Test
  void addUser() {
    Mockito.doNothing().when(userRepo).save(new User("Ritesh Maharjan", "22222"));
    User createdUser = userService.addUser(new User("Ritesh Maharjan", "22222"));
    User foundUser = userService.findUserById(1);
    assertEquals("Ritesh Maharjan", foundUser.getUserName());
    assertEquals("222222", foundUser.getContact());
  }

  @Test
  void whenValidUserIdThenUserShouldBeFound() {
    User foundUser = userService.findUserById(1);
    assertEquals(1,foundUser.getId());
  }


  @Test
  void updateUser() {
  }

  @Test
  void deleteUser() {
  }
}