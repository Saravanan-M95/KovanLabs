package com.sara.onetomanyhellokoding.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sara.onetomanyhellokoding.entity.Department;
import com.sara.onetomanyhellokoding.repository.CompanyRepository;
import com.sara.onetomanyhellokoding.repository.DepartmentRepository;
import com.sara.onetomanyhellokoding.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController 
{
	private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    
    
    @Autowired
    public DepartmentController(CompanyRepository companyRepository,DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }
    
    @PostMapping
    public ResponseEntity<Department> create(@Valid @RequestBody Department department) {
    	
    	
    	Department savedDepartment = departmentRepository.save(department);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedDepartment.getId()).toUri();
        return ResponseEntity.created(location).body(savedDepartment);
    }
}
