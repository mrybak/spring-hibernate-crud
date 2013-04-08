package pl.mrybak.webapps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index.html")
	public String showIndex() {
		// name of a view that we want to show (processed by ViewResolver from dispatch-servlet.xml)
		return "index";
	}
}
