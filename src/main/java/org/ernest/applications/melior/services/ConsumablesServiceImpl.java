package org.ernest.applications.melior.services;


import org.ernest.applications.melior.dao.ConsumableDao;
import org.ernest.applications.melior.entities.Consumable;
import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ConsumablesServiceImpl implements ConsumablesService{

    @Autowired
    ConsumableDao consumableDao;

    @Autowired
    IngredientsService ingredientsService;

    @Override
    public List<Consumable> getConsumables() {
        List<Consumable> consumables = consumableDao.getConsumables().stream()
                                             .sorted(Comparator.comparing(Consumable::getName))
                                             .collect(Collectors.toList());

        Map<String, Ingredient> ingredientsMap = new HashMap<>();
        ingredientsService.getIngredients().forEach(ingredient -> ingredientsMap.put(ingredient.get_id(), ingredient));

        consumables.forEach(consumable -> { consumable.setCost(Double.parseDouble(calculateConsumableCost(consumable, ingredientsMap).replace(',','.')));});

        return consumables;

    }

    private String calculateConsumableCost(Consumable consumable, Map<String, Ingredient> ingredientsMap) {
        return new DecimalFormat("#.##").format(consumable.getIngredientUsedList()
                                                           .stream()
                                                           .map(ingredientUsed -> {
                                                                 double quanity = ingredientUsed.getQuantity();
                                                                 double price =ingredientsMap.get(ingredientUsed.getIngredientId()).getPrice();
                                                                 return price*quanity;})
                                                           .reduce(0.0, Double::sum));
    }

    @Override
    public void createConsumable(String name, double price, List<IngredientUsed> ingredients) {
        consumableDao.createConsumable(name, price, ingredients);
    }

    @Override
    public void deleteConsumable(String id) {
        consumableDao.deleteConsumable(id);
    }

    @Override
    public void updateConsumable(Consumable consumable) {
        consumableDao.updateConsumable(consumable);
    }

}
