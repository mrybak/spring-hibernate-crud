package pl.mrybak.webapps.dao;

import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import pl.mrybak.webapps.model.Department;
import pl.mrybak.webapps.model.Employee;

@Component
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
			session.buildLockRequest(LockOptions.NONE).lock(department);
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
