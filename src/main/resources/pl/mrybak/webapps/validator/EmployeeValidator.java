package pl.mrybak.webapps.validator;

import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.validation.*;

import pl.mrybak.webapps.model.Employee;
 
@Component
public class EmployeeValidator implements Validator {
 
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Employee.class);
    }
 
    public void validate(Object command, Errors errors) {
        Employee employee = (Employee)command;
        if(!StringUtils.hasText(employee.getFirstName())) {
            errors.rejectValue("firstName", "error.required.firstName", "First Name is required");
        }
        if(!StringUtils.hasText(employee.getLastName())) {
            errors.rejectValue("lastName", "error.required.lastName", "Last Name is required");
        }
    }
}