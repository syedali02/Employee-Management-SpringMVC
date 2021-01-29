package net.ali.springboot.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sun.el.stream.Optional;

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

	@Override
	public Employee getEmployeebyID(long Id) {
		java.util.Optional<Employee> empOptional=empRepo.findById(Id);
		Employee employee=null;
		if(empOptional.isPresent()) {
			employee=empOptional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id:" + Id);
		}
		return employee;	
	}

	@Override
	public void deleteEmployee(long id) {
		empRepo.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return this.empRepo.findAll(pageable);
	}


}
