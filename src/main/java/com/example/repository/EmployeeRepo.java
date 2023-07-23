package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	Employee findByEmail(String email);
}
