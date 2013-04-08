package pl.mrybak.webapps.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.mrybak.webapps.dao.EmployeeDao;
import pl.mrybak.webapps.model.Employee;

@Controller
public class ManageEmployeesController {
	@Autowired
	private EmployeeDao employeeDao;

	@ModelAttribute("employees")
	public List<Employee> getEmployees() {
		return employeeDao.findAll();
	}

	@RequestMapping("/manageEmployees.html")
	public String handleRequest() {
		return "manageEmployees";
	}
}
