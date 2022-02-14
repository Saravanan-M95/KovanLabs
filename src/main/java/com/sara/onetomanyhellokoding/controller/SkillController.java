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

import com.sara.onetomanyhellokoding.entity.Skill;
import com.sara.onetomanyhellokoding.repository.EmployeeRepository;
import com.sara.onetomanyhellokoding.repository.SkillRepository;

@RestController
@RequestMapping("/api/v1/skill")
public class SkillController 
{
	private final SkillRepository skillRepository;
    private final EmployeeRepository employeeRepository;
    
    
    @Autowired
    public SkillController(SkillRepository skillRepository,EmployeeRepository employeeRepository) {
        this.skillRepository = skillRepository;
        this.employeeRepository = employeeRepository;
    }
    
    @PostMapping
    public ResponseEntity<Skill> create(@Valid @RequestBody Skill skill) {
    	
    	
    	Skill savedSkill = skillRepository.save(skill);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedSkill.getId()).toUri();
        return ResponseEntity.created(location).body(savedSkill);
    }
}
