package com.example.fordtdd2;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Car {
  private UUID id;
  private String company;
  private String model;

  Car(String company, String model){
    this.company = company;
    this.model = model;
  }
}
