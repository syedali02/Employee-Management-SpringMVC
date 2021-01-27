package net.ali.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.ali.springboot.model.Employee;
import net.ali.springboot.service.EmployeeService;

@Controller
public class EmployeeController {

	//display list of employees
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/")
	public String viewHomePage(Model model)
	{
		model.addAttribute("ListEmployees", empService.getAllEmployee());
		return  "index";
	}
	
	@GetMapping("/ShowNewEmployeeForm")
	public String showNewEmployeeForm(Model m){
	//We create Model attribute to bind form data
		Employee employee= new Employee();
		m.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee ) {
		//save employee to the database
		empService.saveEmployee(employee);
		return "redirect:/";
	}
	
}
