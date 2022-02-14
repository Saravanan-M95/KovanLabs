package com.sara.onetomanyhellokoding.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
public class Department 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String departmentName;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employee = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "companyid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Company company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee)
	{
		this.employee = employee;
		
		for(Employee e : employee) 
		{
            e.setDepartment(this);
        }
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
