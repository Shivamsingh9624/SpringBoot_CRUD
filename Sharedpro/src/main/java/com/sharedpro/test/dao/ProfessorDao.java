package com.sharedpro.test.dao;

import com.sharedpro.test.entity.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDao extends JpaRepository<Professor, Long> {

  List<Professor> findByDepartment_Id(Long departmentId);
}
