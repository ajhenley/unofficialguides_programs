package com.example.demo;

import javax.persistence.*;

@Entity
public class Laptop {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String brand;

  private String model;

  @OneToOne(mappedBy = "laptop")
  private Employee employee;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
