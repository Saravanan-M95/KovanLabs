package com.sara.onetomanyhellokoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sara.onetomanyhellokoding.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
}