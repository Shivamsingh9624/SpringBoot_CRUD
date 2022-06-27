package com.sharedpro.test.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue
  @Column(name = "employee_id")
  private Long id;
  private String name;
  private String location;
  private Date dob;

  @ManyToOne
  @JoinColumn(name="department_id")
  private Department department;

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

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

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Employee)) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(getId(), employee.getId()) && Objects.equals(getName(),
        employee.getName()) && Objects.equals(getLocation(), employee.getLocation())
        && Objects.equals(getDob(), employee.getDob());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getLocation(), getDob());
  }
}
