package com.sara.onetomanyhellokoding.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Company 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Department> department = new HashSet<>();
    

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

    public Set<Employee> getEmployee() {
        return employees;
    }

    public void setEmployee(Set<Employee> employees) {
        this.employees = employees;

        for(Employee e : employees) {
            e.setCompany(this);
        }
    }

	public Set<Department> getDepartment() {
		return department;
	}

	public void setDepartment(Set<Department> department) 
	{
		this.department = department;
		
		for(Department e : department) {
            e.setCompany(this);
        }
	}


    
    
}