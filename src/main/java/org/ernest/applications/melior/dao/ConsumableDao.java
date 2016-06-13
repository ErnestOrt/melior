package org.ernest.applications.melior.dao;

import org.ernest.applications.melior.entities.Consumable;
import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.entities.IngredientUsed;

import java.util.List;

public interface ConsumableDao {

    List<Consumable> getConsumables();

    void createConsumable(String name, double price, List<IngredientUsed> ingredients);

    void deleteConsumable(String id);

    void updateConsumable(Consumable consumable);
}
