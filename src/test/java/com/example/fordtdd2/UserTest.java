package com.example.fordtdd2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void whenBuyCarAddToList() throws Exception {
    Car car = new Car("Ford", "Figo");
    User user = new User("Elton");
    user.buyCar(car);
    assertThat(user.getCars(), hasSize(1));
    assertThat(user.getCars().get(0).getCompany(), equalTo(car.getCompany()));
    assertThat(user.getCars().get(0).getModel(), equalTo(car.getModel()));
  }

  @Test
  void whenBuyMultipleCarsAddToList() throws Exception {
    User user = new User("Elton");
    Car car = new Car("Ford", "Figo");
    user.buyCar(car);
    Car secondCar = new Car("Ford", "Eco Sport");
    user.buyCar(secondCar);
    assertThat(user.getCars(), hasSize(2));
  }

  @Test
  void whenBuyExistingCarThrowError() throws Exception {
    User user = new User("Elton");
    Car car = new Car("Ford", "Figo");
    user.buyCar(car);
    Car secondCar = new Car("Ford", "Figo");
    Throwable exception = assertThrows(Exception.class, ()->{user.buyCar(secondCar);});
    assertTrue(exception.getMessage().contains("Car already Exist"));
  }

  @Test
  void WhenBuyNullCarThrowError() {
    User user = new User("Elton");
    Car car = null;
    Throwable exception = assertThrows(Exception.class, ()->{user.buyCar(car);});
    assertThat(exception.getMessage(), is(not(emptyString())));
  }

  @Test
  void testSellCar() {
  }

  @Test
  void updateCarInfo() {
  }
}