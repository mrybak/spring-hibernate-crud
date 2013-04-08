package pl.mrybak.webapps.dao;

import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;

import pl.mrybak.webapps.model.Department;
import pl.mrybak.webapps.model.Employee;

public class DepartmentDao extends MappedModelDao<Department> {

	@Override
	public Class<Department> getActualClass() {
		return Department.class;
	}

	@Override
	public void delete(Department entity) {
		removeEmployeesFromDepartment(entity);
		super.delete(entity);
	}

	private void removeEmployeesFromDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();

		Set<Employee> employees = department.getEmployees();
		if (!Hibernate.isInitialized(employees)) {
			session.lock(department, LockMode.NONE);
			Hibernate.initialize(employees);
		}

		if (employees != null) {
			for (Employee employee : employees) {
				Set<Department> departments = employee.getDepartments();
				departments.remove(department);
				employee.setDepartments(departments); // isn't that redundant?
				session.saveOrUpdate(employee);
			}
		}
	}

}
