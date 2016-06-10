package org.ernest.applications.melior.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientsController {

	@RequestMapping({ "/ingredients"})
	public String getDashboard(Model model) {

		return "ingredients";
	}
}