package pl.mrybak.webapps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.mrybak.webapps.dao.MappedModelDao;
import pl.mrybak.webapps.model.Department;

@Controller
@RequestMapping("/editDepartment.html")
public class EditDepartment extends MappedModelFormController<Department> {
 
    @Override
    public Class<Department> getActualClass() {
        return Department.class;
    }
 
    @Override
    @Autowired
    @Qualifier("departmentValidator")
    public void setMappedModelValidator(Validator validator) {
        this.validator = validator;
    }
 
    @Override
    @Autowired
    @Qualifier("departmentDao")
    public void setModelDao(MappedModelDao<Department> modelDao) {
        this.modelDao = modelDao;
    }

}