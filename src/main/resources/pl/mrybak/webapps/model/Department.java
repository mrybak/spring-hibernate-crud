package pl.mrybak.webapps.model;

import java.util.Set;

import javax.persistence.*;
 
@Entity
@Table(name="department")
public class Department extends MappedModel {    
 
    @Column(name="name", length=150, nullable=false, unique=true)   // department name has to be unique!
    private String name;
 
    // this is the 'owned' side of relationship (Department is mapped by Employee.departments)
    @ManyToMany(mappedBy="departments",fetch=FetchType.LAZY)
    private Set<Employee> employees;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Set<Employee> getEmployees() {
        return employees;
    }
 
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
 
}
