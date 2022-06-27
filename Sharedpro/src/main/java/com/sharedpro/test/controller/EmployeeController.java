package com.sharedpro.test.controller;

import com.sharedpro.test.dao.EmployeeDao;
import com.sharedpro.test.entity.Employee;
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
public class EmployeeController {

  @Autowired
  private EmployeeDao repo;


	@GetMapping("/employee/{employeeId}")
	public Employee getEmployee(@PathVariable String employeeId) {
		return repo.findById(Long.parseLong(employeeId)).orElseThrow();
	}

	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return repo.saveAndFlush(employee);
	}

  @GetMapping("/employee/department/{departmentId}")
  public List<Employee> findByDepartment(@PathVariable String departmentId) {
    return repo.findByDepartment_Id(Long.parseLong(departmentId));
  }

	@GetMapping("/employee")
  public List<Employee> sort(@RequestParam final Map<String, String> allParams) {
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


  @PutMapping("/employee")
  public Employee updateEmployee(@RequestBody Employee employee) {
    return repo.saveAndFlush(employee);
  }


  @DeleteMapping("/employee/{employeeId}")
  public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String employeeId) {
    try {
			repo.deleteById(Long.parseLong(employeeId));
      return new ResponseEntity<>(HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
