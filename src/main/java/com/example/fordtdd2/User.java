package com.example.fordtdd2;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@NoArgsConstructor
public class User {
  private UUID id;
  private String name;
  private List<Car> cars;

  User(String name){
    this.id = UUID.randomUUID();
    this.name = name;
    this.cars = new ArrayList<Car>();
  }

  public void buyCar(Car car) throws Exception {
    Optional<Car> checkedCar = this.cars.stream().filter(c -> c.getCompany() == car.getCompany() && c.getModel() == car.getModel()).findFirst();
    if(checkedCar.isPresent()){
      throw new Exception("Car already Exist");
    }
    car.setId(UUID.randomUUID());
    this.cars.add(car);
  }

  public void sellCar(Car car) throws Exception{
    if(!this.cars.contains(car)){
      throw new Exception("Car does not Exist");
    }
    this.cars.remove(car);
  }

  public void sellCar(UUID carId) throws  Exception{
    Optional<Car> car = this.cars.stream().filter(c -> c.getId() == carId).findFirst();
    if(car.isPresent()){
      this.cars.remove(car);
    } else {
      throw new Exception("Car does not Exist");
    }
  }

  public void updateCarInfo(Car car) throws Exception{
    Optional<Car> isCarPresent = this.cars.stream().filter(c -> c.getId() == car.getId()).findFirst();
    if(isCarPresent.isPresent()){
      this.cars.remove(isCarPresent.get());
      this.cars.add(car);
    } else {
      throw new Exception("Car does not Exist");
    }
  }


}
