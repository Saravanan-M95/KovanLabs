package com.sara.onetomanyhellokoding.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(name = "designation")
    private String designation;

    @NotNull
    @Column(name = "name")
    private String name;
    
    @NotNull
    @Column(name = "salary")
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "companyid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Company company;
    
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "departmentid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Department department;
    
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "skillid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Skill skill1;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Skill> skill = new HashSet<>();

    public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }


	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Skill> getSkill() {
		return skill;
	}

	public void setSkill(Set<Skill> skill) {
		this.skill = skill;
		
		for(Skill s : skill) {
            s.setEmployee(this);
        }
	}

	public Skill getSkill1() {
		return skill1;
	}

	public void setSkill1(Skill skill1) {
		this.skill1 = skill1;
	}
    
	
}