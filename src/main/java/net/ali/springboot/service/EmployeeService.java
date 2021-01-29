package net.ali.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.ali.springboot.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployee(); 
	void saveEmployee(Employee e);
	Employee getEmployeebyID(long Id);
	void deleteEmployee(long id);
	Page<Employee> findPaginated(int pageNo, int pageSize);
}
