package org.ernest.applications.melior.controllers;


import org.ernest.applications.melior.services.ConsumablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CashController {

	@Autowired
	ConsumablesService consumablesService;

	@RequestMapping({ "/cash", "/" })
	public String getCashView(Model model) {

		model.addAttribute("consumables", consumablesService.getConsumables());
		return "cash";
	}
}