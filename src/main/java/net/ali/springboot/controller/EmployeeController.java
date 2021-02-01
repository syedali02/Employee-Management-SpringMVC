package net.ali.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return findPagination(1,"firstName","asc", model);
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
	
	@GetMapping("/showFormforUpdate/{id}")
	public String showFormforUpdate(@PathVariable (value="id") long id, Model model){
		// get employee from the service 
		Employee employee = empService.getEmployeebyID(id);
		
		//set employee to model attribute to pre-populate the form data
		
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployeeById(@PathVariable (value="id") long Id) {
		empService.deleteEmployee(Id);
		return "redirect:/";
	}
	
	// Our url is going to be /page/1?sortField=name&sortDir=asc
	@GetMapping("/page/{pageNo}")
	public String findPagination(@PathVariable( value="pageNo") int pageNo,
			 @RequestParam("sortField") String sortField, 
			 @RequestParam("sortDir") String sortDir,Model model) {
		int pageSize=5;
		
		Page<Employee> page= empService.findPaginated(pageNo, pageSize,sortField,sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalitems", page.getTotalElements());
		model.addAttribute("ListEmployees", listEmployees);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDir);
		model.addAttribute("reversesortDir", sortDir.equals("asc")?"desc":"asc");
		
		return "index";
	}
	
}
