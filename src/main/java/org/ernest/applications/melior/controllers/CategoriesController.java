package org.ernest.applications.melior.controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ernest.applications.melior.entities.Category;
import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.ernest.applications.melior.services.CategoryService;
import org.ernest.applications.melior.services.ConsumablesService;
import org.ernest.applications.melior.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoriesController {

	@Autowired
	ConsumablesService consumablesService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping({ "/categories"})
	public String getIngredientsView(Model model) {

		model.addAttribute("consumables", consumablesService.getConsumables());
		model.addAttribute("categories", categoryService.getCategories());

		return "categories";
	}

	@RequestMapping(value= "/category/create", method = RequestMethod.POST)
	@ResponseBody
	public void createConsumable(@RequestParam(value="name") String name,
								 @RequestParam(value="consumbalesAdded") String consumables) {

		categoryService.createCategory(name, new Gson().fromJson(consumables, new TypeToken<List<String>>(){}.getType()));
	}

	@RequestMapping(value= "/category/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteConsumable(@RequestParam(value="id") String id) {

		categoryService.deleteConsumable(id);
	}

	@RequestMapping(value= "/category/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateConsumable(@RequestParam(value="id") String id,
								 @RequestParam(value="name") String name,
								 @RequestParam(value="consumbalesAdded") String consumables) {

		categoryService.updateCategory(new Category(id, name, new Gson().fromJson(consumables, new TypeToken<List<String>>(){}.getType())));
	}
}