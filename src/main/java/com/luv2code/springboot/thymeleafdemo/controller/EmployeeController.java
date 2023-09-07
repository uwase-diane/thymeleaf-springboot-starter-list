package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;


	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> employees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model theModel){

		Employee em = employeeService.findById(employeeId);
		theModel.addAttribute("employee", em);
		return "employees/employee-form";
	}


	@GetMapping("/showFormForDelete")
	public String showFormForDelete(@RequestParam("employeeId") int employeeId){
		employeeService.deleteById(employeeId);
		return "redirect:/employees/list";

	}

	@PostMapping("save")
		public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

			employeeService.save(theEmployee);
			return "redirect:/employees/list";
		}

}









