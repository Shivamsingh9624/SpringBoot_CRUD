package com.sharedpro.test.controller;

import com.sharedpro.test.dao.DepartmentDao;
import com.sharedpro.test.entity.Department;
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
public class DepartmentController {

  @Autowired
  private DepartmentDao repo;

  @GetMapping("/department/{departmentId}")
  public Department getDepartment(@PathVariable String departmentId) {
    return repo.findById(Long.parseLong(departmentId)).orElseThrow();
  }

  @PostMapping("/department")
  public Department addDepartment(@RequestBody Department department) {
    return repo.saveAndFlush(department);
  }

  @PutMapping("/department")
  public Department updateDepartment(@RequestBody Department department) {
    return repo.saveAndFlush(department);
  }

  @DeleteMapping("/department/{departmentId}")
  public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable String departmentId) {
    try {
      repo.deleteById(Long.parseLong(departmentId));
      return new ResponseEntity<>(HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/department")
  public List<Department> sort(@RequestParam final Map<String, String> allParams) {
    final Optional<String> sortBy = Optional.ofNullable(allParams.get("sortBy"));
    final Optional<String> orderBy = Optional.ofNullable(allParams.get("orderBy"));

    final boolean isSortKeysPresent = orderBy.isPresent() && sortBy.isPresent();

    if (isSortKeysPresent && orderBy.get().equals("asc")) {
      return repo.findAll(Sort.by(Sort.Direction.ASC, sortBy.get()));
    } else if (isSortKeysPresent && orderBy.get().equals("desc")) {
      return repo.findAll(Sort.by(Sort.Direction.DESC, sortBy.get()));
    }
    return repo.findAll();
  }

}
