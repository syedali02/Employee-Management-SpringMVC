package net.ali.springboot.service;

import java.util.List;

import net.ali.springboot.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployee(); 
	void saveEmployee(Employee e);

}
