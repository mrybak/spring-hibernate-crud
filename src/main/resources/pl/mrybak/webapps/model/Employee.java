package pl.mrybak.webapps.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "employee")
// redundant in this case
public class Employee extends MappedModel {

	@Column(name = "first_name", length = 150, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 150, nullable = false)
	private String lastName;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(   // create table for many-to-many relationship
			name="employee_department",
			joinColumns={@JoinColumn(name="employee_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="department_id", referencedColumnName="id")}
	)
	private Set<Department> departments;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
