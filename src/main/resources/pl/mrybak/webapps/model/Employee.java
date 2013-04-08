package pl.mrybak.webapps.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "employee")
// redundant in this case
public class Employee extends MappedModel {

	@Column(name = "first_name", length = 150, nullable = false)
	private String firstName;

	@Column(name = "second_name", length = 150, nullable = false)
	private String secondName;

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

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
