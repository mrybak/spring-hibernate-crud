package pl.mrybak.webapps.dao;

import org.springframework.stereotype.Component;

import pl.mrybak.webapps.model.Employee;

@Component  // makes class available for auto-wiring into other classes
public class EmployeeDao extends MappedModelDao<Employee> {

	@Override
	public Class<Employee> getActualClass() {
		return Employee.class;
	}

}
