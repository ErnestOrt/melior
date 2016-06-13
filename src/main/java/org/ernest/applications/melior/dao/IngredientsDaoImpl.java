package org.ernest.applications.melior.dao;

import org.ernest.applications.melior.entities.Ingredient;
import org.lightcouch.CouchDbClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class IngredientsDaoImpl extends DaoBase implements IngredientsDao {

    @Value("${db.name.ingredients}")
    private String dbName;

    @Value("${db.host}")
    private String dbHost;

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredientsList = new ArrayList<>();

        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        ingredientsList = dbClient.view("_all_docs").includeDocs(true).query(Ingredient.class);
        dbClient.shutdown();

        return ingredientsList;
    }

    @Override
    public void createIngredient(String name, double price) {
        Ingredient ingredient = new Ingredient();
        ingredient.set_id(UUID.randomUUID().toString());
        ingredient.setName(name);
        ingredient.setPrice(price);

        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        dbClient.save(ingredient);
        dbClient.shutdown();
    }

    @Override
    public void deleteIngredient(String id) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        Ingredient ingredient = dbClient.find(Ingredient.class, id);
        dbClient.remove(ingredient);
        dbClient.shutdown();
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));

        Ingredient ingredientToBeUpdated = dbClient.find(Ingredient.class, ingredient.get_id());
        ingredientToBeUpdated.setName(ingredient.getName());
        ingredientToBeUpdated.setPrice(ingredient.getPrice());

        dbClient.update(ingredientToBeUpdated);
        dbClient.shutdown();
    }

    @Override
    public Ingredient getIngredient(String id) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        Ingredient ingredient = dbClient.find(Ingredient.class, id);
        dbClient.shutdown();

        return ingredient;
    }
}
