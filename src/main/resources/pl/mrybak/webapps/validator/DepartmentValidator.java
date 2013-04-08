package pl.mrybak.webapps.validator;

import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.validation.*;

import pl.mrybak.webapps.model.Department;
 
@Component
public class DepartmentValidator implements Validator {
 
    public boolean supports(Class<?> clazz) {
        return (clazz.isAssignableFrom(Department.class));
    }
 
    public void validate(Object command, Errors errors) {
        Department department = (Department)command;
        if(!StringUtils.hasText(department.getName())) {
            errors.rejectValue("name", "error.required.name", "Name is required");
        }
 
    }
}
