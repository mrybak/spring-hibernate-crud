package pl.mrybak.webapps.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mrybak.webapps.dao.MappedModelDao;
import pl.mrybak.webapps.model.MappedModel;

@Controller
public abstract class MappedModelFormController<T extends MappedModel> {

	protected Validator validator;

	protected MappedModelDao<T> modelDao;

	public abstract Class<T> getActualClass();

	public abstract void setMappedModelValidator(Validator validator);

	public abstract void setModelDao(MappedModelDao<T> modelDao);

	private String getTypeName() {
		return getActualClass().getSimpleName();
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=edit")  // processes all GET request with action=edit
	public String showForm(Model model,
			@RequestParam(value = "id", required = false) String id) {
		T object = null;
		if (id == null) {
			// add new object
			try {
				object = getActualClass().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// edit existing object
			object = modelDao.findById(id);
		}

		// just like @ModelAttribute annotation...
		model.addAttribute("mappedModel", object);
		model.addAttribute("mappedModelName", getTypeName());
		return getEditView();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleSubmit(Model model,
			@ModelAttribute("mappedModel") T mappedModel,
			BindingResult bindingResult,
			@RequestParam(value = "submit", required = true) String submitType) {
		String result = makeRedirect(getManageView());
		if (submitType.toLowerCase().equals("save")) {
			validate(mappedModel, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAllAttributes(bindingResult.getAllErrors());
				model.addAttribute(mappedModel);
				model.addAttribute("mappedModelName", getTypeName());
				model.addAttribute("action", "edit");
				result = getEditView();
			} else {
				try {
					modelDao.saveOrUpdate(mappedModel);
				} catch (DataIntegrityViolationException dive) {
					String message = dive.getMostSpecificCause().getMessage();
					bindingResult.reject("error.constraint.violation", message);
					model.addAllAttributes(bindingResult.getAllErrors());
					model.addAttribute("mappedModel", mappedModel);
					model.addAttribute("mappedModelName", getTypeName());
					result = getEditView();
				}
			}
		}
		return result;
	}

	protected void validate(T mappedModel, BindingResult bindingResult) {
		if (this.validator != null) {
			this.validator.validate(mappedModel, bindingResult);
		}
	}

	public String makeRedirect(String viewName) {
		return "redirect:/" + viewName + ".html";
	}

	public String getEditView() {
		return "edit" + getTypeName();
	}

	public String getDeleteView() {
		return "delete" + getTypeName();
	}

	public String getManageView() {
		return "manage" + getTypeName() + "s";
	}
}
