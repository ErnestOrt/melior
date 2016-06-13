package org.ernest.applications.melior.controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ernest.applications.melior.entities.Consumable;
import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.ernest.applications.melior.services.ConsumablesService;
import org.ernest.applications.melior.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class ConsumablesController {

	@Autowired
	ConsumablesService consumablesService;

	@Autowired
	IngredientsService ingredientsService;

	@RequestMapping({ "/menu" })
	public String getMenuView(Model model) {

		model.addAttribute("consumables", consumablesService.getConsumables());
		model.addAttribute("ingredients", ingredientsService.getIngredients());

		return "menu";
	}

	@RequestMapping(value= "/consumable/create", method = RequestMethod.POST)
	@ResponseBody
	public void createConsumable(@RequestParam(value="name") String name,
								 @RequestParam(value="price") double price,
								 @RequestParam(value="ingredients") String ingredients) {

		consumablesService.createConsumable(name, price, new Gson().fromJson(ingredients, new TypeToken<List<IngredientUsed>>(){}.getType()));
	}

	@RequestMapping(value= "/consumable/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateConsumable(@RequestParam(value="id") String id,
			                     @RequestParam(value="name") String name,
								 @RequestParam(value="price") double price,
								 @RequestParam(value="ingredients") String ingredients) {

		consumablesService.updateConsumable(new Consumable(id, name, price, new Gson().fromJson(ingredients, new TypeToken<List<IngredientUsed>>(){}.getType())));
	}

	@RequestMapping(value= "/consumable/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteConsumable(@RequestParam(value="id") String id) {

		consumablesService.deleteConsumable(id);
	}
}