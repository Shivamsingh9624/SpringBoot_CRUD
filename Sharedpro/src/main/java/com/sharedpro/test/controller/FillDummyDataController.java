package com.sharedpro.test.controller;

import com.sharedpro.test.dao.DepartmentDao;
import com.sharedpro.test.dao.EmployeeDao;
import com.sharedpro.test.dao.ProfessorDao;
import com.sharedpro.test.entity.Department;
import com.sharedpro.test.entity.Employee;
import com.sharedpro.test.entity.Professor;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FillDummyDataController {

  @Autowired
  private DepartmentDao departmentDao;

  @Autowired
  private EmployeeDao employeeDao;

  @Autowired
  private ProfessorDao professorDao;


  @GetMapping("/")
  public void add() {

    Department department1 = new Department();
    department1.setLocation("Ahmedabad");
    department1.setName("CSE");

    Department department2 = new Department();
    department2.setId(1L);
    department2.setLocation("Baroda");
    department2.setName("IT");

    departmentDao.saveAll(List.of(department1, department2));

    Employee employee1 = new Employee();
    employee1.setName("Shivam Singh");
    employee1.setLocation("Ahmedabad");
    employee1.setDob(new Date());
    employee1.setDepartment(department1);

    Employee employee2 = new Employee();
    employee2.setName("Test user 1");
    employee2.setLocation("Rajkot");
    employee2.setDob(new Date());
    employee2.setDepartment(department2);

    Employee employee3 = new Employee();
    employee3.setName("Test user 2");
    employee3.setLocation("Dahod");
    employee3.setDob(new Date());
    employee3.setDepartment(department1);

    employeeDao.saveAll(List.of(employee1, employee2, employee3));

    Professor professor1 = new Professor();
    professor1.setName("Prof 1");
    professor1.setDepartment(department1);

    Professor professor2 = new Professor();
    professor2.setName("Prof 2");
    professor2.setDepartment(department2);

    Professor professor3 = new Professor();
    professor3.setName("Prod 3");
    professor3.setDepartment(department1);

    professorDao.saveAll(List.of(professor1, professor2, professor3));

  }

}
