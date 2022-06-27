package com.sharedpro.test.dao;

import com.sharedpro.test.entity.Employee;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

  List<Employee> findByDepartment_Id(Long departmentId);
}
