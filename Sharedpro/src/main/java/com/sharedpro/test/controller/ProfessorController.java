package com.sharedpro.test.controller;

import com.sharedpro.test.dao.ProfessorDao;
import com.sharedpro.test.entity.Professor;
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
public class ProfessorController {

  @Autowired
  private ProfessorDao repo;

  @GetMapping("/professor")
  public List<Professor> sort(@RequestParam final Map<String, String> allParams) {
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

  @GetMapping("/professor/{professorId}")
  public Professor getProfessor(@PathVariable String professorId) {
    return repo.findById(Long.parseLong(professorId)).orElseThrow();
  }

  @PostMapping("/professor")
  public Professor addProfessor(@RequestBody Professor professor) {
    return repo.saveAndFlush(professor);
  }

  @PutMapping("/professor")
  public Professor updateProfessor(@RequestBody Professor professor) {
    return repo.saveAndFlush(professor);
  }

  @GetMapping("/professor/department/{departmentId}")
  public List<Professor> findByDepartment(@PathVariable String departmentId) {
    return repo.findByDepartment_Id(Long.parseLong(departmentId));
  }

  @DeleteMapping("/professor/{professorId}")
  public ResponseEntity<HttpStatus> deleteProfessor(@PathVariable String professorId) {
    try {
      repo.deleteById(Long.parseLong(professorId));
      return new ResponseEntity<>(HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}