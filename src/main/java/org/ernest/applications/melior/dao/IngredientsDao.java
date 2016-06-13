package org.ernest.applications.melior.dao;

import org.ernest.applications.melior.entities.Ingredient;

import java.util.List;

public interface IngredientsDao {

    List<Ingredient> getIngredients();

    void createIngredient(String name, double price);

    void deleteIngredient(String id);

    void updateIngredient(Ingredient ingredient);

    Ingredient getIngredient(String id);
}
