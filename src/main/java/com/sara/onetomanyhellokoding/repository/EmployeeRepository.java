package com.sara.onetomanyhellokoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sara.onetomanyhellokoding.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}