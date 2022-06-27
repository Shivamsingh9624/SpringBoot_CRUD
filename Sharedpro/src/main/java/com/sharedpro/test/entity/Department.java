package com.sharedpro.test.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

  @Id
  @GeneratedValue
  @Column(name = "department_id")
  private Long id;
  private String name;
  private String location;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", location='" + location + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Department)) {
      return false;
    }
    Department that = (Department) o;
    return Objects.equals(getId(), that.getId()) && Objects.equals(getName(),
        that.getName()) && Objects.equals(getLocation(), that.getLocation());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getLocation());
  }
}
