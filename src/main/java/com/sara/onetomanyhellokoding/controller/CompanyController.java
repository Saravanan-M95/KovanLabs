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

import com.sara.onetomanyhellokoding.entity.Company;
import com.sara.onetomanyhellokoding.repository.EmployeeRepository;
import com.sara.onetomanyhellokoding.repository.SkillRepository;
import com.sara.onetomanyhellokoding.repository.CompanyRepository;
import com.sara.onetomanyhellokoding.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository,SkillRepository skillRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.skillRepository = skillRepository;
    }

    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody Company company) {
    	
    	System.out.println("Company Contoller : "+company.getName());
    	
        Company savedCompany = companyRepository.saveAndFlush(company);
        
        System.out.println(savedCompany.getName());
        
        System.out.println("Company Contoller 2: "+company.getName());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedCompany.getId()).toUri();
        System.out.println("Company Contoller 3: "+company.getName());
        
        
        return ResponseEntity.created(location).body(savedCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable Integer id, @Valid @RequestBody Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        company.setId(optionalCompany.get().getId());
        companyRepository.save(company);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> delete(@PathVariable Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        companyRepository.delete(optionalCompany.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalCompany.get());
    }

    @GetMapping
    public ResponseEntity<Page<Company>> getAll(Pageable pageable) {
        return ResponseEntity.ok(companyRepository.findAll(pageable));
    }
}