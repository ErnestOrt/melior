package org.ernest.applications.melior.services;


import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.entities.IngredientUsed;

import java.util.List;

public interface IngredientsService {

    List<Ingredient> getIngredients();

    void createIngredient(String name, double price);

    void deleteIngredient(String id);

    void updateIngredient(Ingredient ingredient);

    Ingredient getIngredient(String id);
}
