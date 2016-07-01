package org.ernest.applications.melior.dao;


import org.ernest.applications.melior.entities.Category;
import org.ernest.applications.melior.entities.Consumable;
import org.ernest.applications.melior.entities.Ingredient;
import org.lightcouch.CouchDbClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CategoryDaoImpl extends DaoBase implements CategoryDao{

    @Value("${db.name.categories}")
    private String dbName;

    @Value("${db.host}")
    private String dbHost;

    @Override
    public void createCategory(String name, List<String> consumablesIds) {
        Category category = new Category();
        category.set_id(UUID.randomUUID().toString());
        category.setName(name);
        category.setConsumablesIds(consumablesIds);

        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        dbClient.save(category);
        dbClient.shutdown();
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categoriesList = new ArrayList<>();

        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        categoriesList = dbClient.view("_all_docs").includeDocs(true).query(Category.class);
        dbClient.shutdown();

        return categoriesList;
    }

    @Override
    public void deleteConsumable(String id) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        Category category = dbClient.find(Category.class, id);
        dbClient.remove(category);
        dbClient.shutdown();
    }

    @Override
    public void updateCategory(Category category) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));

        Category categoryToBeUpdated = dbClient.find(Category.class, category.get_id());
        categoryToBeUpdated.setName(category.getName());
        categoryToBeUpdated.setConsumablesIds(category.getConsumablesIds());

        dbClient.update(categoryToBeUpdated);
        dbClient.shutdown();
    }
}
