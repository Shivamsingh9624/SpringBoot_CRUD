package com.sharedpro.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharedpro.test.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, Long> {

}
