package org.ernest.applications.melior.services;


import org.ernest.applications.melior.dao.IngredientsDao;
import org.ernest.applications.melior.entities.Ingredient;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientsServiceImpl implements IngredientsService{

    @Autowired
    IngredientsDao ingredientsDao;

    @Override
    public List<Ingredient> getIngredients() {
        return ingredientsDao.getIngredients().stream()
                                              .sorted(Comparator.comparing(Ingredient::getName))
                                              .collect(Collectors.toList());
    }

    @Override
    public void createIngredient(String name, double price) {
        ingredientsDao.createIngredient(name, price);
    }

    @Override
    public void deleteIngredient(String id) {
        ingredientsDao.deleteIngredient(id);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientsDao.updateIngredient(ingredient);
    }

    @Override
    public Ingredient getIngredient(String id) {
        return ingredientsDao.getIngredient(id);
    }

}
