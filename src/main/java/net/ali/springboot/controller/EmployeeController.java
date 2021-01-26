package net.ali.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}
