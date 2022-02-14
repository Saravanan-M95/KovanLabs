package com.sara.onetomanyhellokoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sara.onetomanyhellokoding.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> 
{

}
