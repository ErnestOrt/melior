package org.ernest.applications.melior.controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ernest.applications.melior.entities.Category;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.ernest.applications.melior.services.CategoryService;
import org.ernest.applications.melior.services.ConsumablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CashController {

	@Autowired
	ConsumablesService consumablesService;

	@Autowired
	CategoryService categoriesService;

	@RequestMapping({ "/cash", "/" })
	public String getCashView(Model model) {

		model.addAttribute("categories", categoriesService.getCategories());
		model.addAttribute("consumables", consumablesService.getConsumables());
		return "cash";
	}

	@RequestMapping(value= "/cash/bill", method = RequestMethod.POST)
	@ResponseBody
	public void createBill(@RequestParam(value="rateSelected") String rateSelected,
								 @RequestParam(value="tableSelected") String tableSelected,
								 @RequestParam(value="consumables") String consumables) {

		System.out.println(rateSelected + " ->>> " + tableSelected + " ->>> " + consumables);

	}
}