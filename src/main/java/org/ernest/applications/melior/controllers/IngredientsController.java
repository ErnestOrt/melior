package org.ernest.applications.melior.controllers;


import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IngredientsController {

	@Autowired
	IngredientsService ingredientsService;

	@RequestMapping({ "/ingredients"})
	public String getIngredientsView(Model model) {

		model.addAttribute("ingredients", ingredientsService.getIngredients());

		return "ingredients";
	}


	@RequestMapping(value= "/ingredients/create", method = RequestMethod.POST)
	@ResponseBody
	public void createIngredient(@RequestParam(value="name") String name, @RequestParam(value="price") double price) {
		ingredientsService.createIngredient(name, price);
	}

	@RequestMapping(value= "/ingredients/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteIngredient(@RequestParam(value="id") String id) {
		ingredientsService.deleteIngredient(id);
	}

	@RequestMapping(value= "/ingredients/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateIngredient(@RequestParam(value="id") String id, @RequestParam(value="name") String name, @RequestParam(value="price") double price) {
		ingredientsService.updateIngredient(new Ingredient(id, name, price));
	}
}