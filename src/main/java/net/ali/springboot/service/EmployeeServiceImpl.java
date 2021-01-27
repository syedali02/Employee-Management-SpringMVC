package net.ali.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ali.springboot.model.Employee;
import net.ali.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired 
	EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	@Override
	public void saveEmployee(Employee e) {
		this.empRepo.save(e);
	}

}
