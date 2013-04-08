package pl.mrybak.webapps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.mrybak.webapps.dao.DepartmentDao;
import pl.mrybak.webapps.model.Department;

@Controller
public class ManageDepartmentsController {

	@Autowired
	private DepartmentDao departmentDao;
	
	@ModelAttribute("departments")  // enables using ${departments} in our jsp files
	public List<Department> getDepartments() {
		return departmentDao.findAll();
	}
	
	@RequestMapping("/manageDepartments.html")
	public String handleRequest() {
		return "manageDepartments";
	}
}
