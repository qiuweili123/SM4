package com.graphql;

import java.util.List;

public class Person {

  private int id;
  private String name;
  private String password;
  private List<Dog> dogs;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Dog> getDogs() {
    return dogs;
  }

  public void setDogs(List<Dog> dogs) {
    this.dogs = dogs;
  }
}