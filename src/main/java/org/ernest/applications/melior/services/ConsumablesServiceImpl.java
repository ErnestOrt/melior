package org.ernest.applications.melior.services;


import org.ernest.applications.melior.dao.ConsumableDao;
import org.ernest.applications.melior.entities.Consumable;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        consumables.forEach(consumable -> {
                             consumable.setCost(consumable.getIngredientUsedList().stream()
                                                               .map(ingredientUsed -> {return ingredientUsed.getQuantity() * ingredientsService.getIngredient(ingredientUsed.getIngredientId()).getPrice();})
                                                               .reduce(0.0, Double::sum));});
        return consumables;

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
