package com.sara.onetomanyhellokoding.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sara.onetomanyhellokoding.entity.Employee;
import com.sara.onetomanyhellokoding.entity.Company;
import com.sara.onetomanyhellokoding.repository.EmployeeRepository;
import com.sara.onetomanyhellokoding.repository.SkillRepository;
import com.sara.onetomanyhellokoding.repository.CompanyRepository;
import com.sara.onetomanyhellokoding.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final SkillRepository skillRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeController(DepartmentRepository departmentRepository,EmployeeRepository employeeRepository,SkillRepository skillRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.skillRepository = skillRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid Employee employee) {
    	
    	System.out.println("Emp Contoller : "+employee.getName());
    	
        Optional<Company> optionalCompany = companyRepository.findById(employee.getCompany().getId());
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setCompany(optionalCompany.get());
        

        Employee savedEmployee = employeeRepository.save(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody @Valid Employee employee, @PathVariable Integer id) 
    {
    	System.out.println("Put Mapping : Emp Controller");
        Optional<Company> optionalCompany = companyRepository.findById(employee.getCompany().getId());
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setCompany(optionalCompany.get());
        employee.setId(optionalEmployee.get().getId());
        employeeRepository.save(employee);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employeeRepository.delete(optionalEmployee.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAll(Pageable pageable) {
        return ResponseEntity.ok(employeeRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEmployee.get());
    }
}