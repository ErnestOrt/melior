package org.ernest.applications.melior.dao;

import org.ernest.applications.melior.entities.Consumable;
import org.ernest.applications.melior.entities.Ingredient;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.lightcouch.CouchDbClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ConsumableDaoImpl extends DaoBase implements ConsumableDao {

    @Value("${db.name.consumables}")
    private String dbName;

    @Value("${db.host}")
    private String dbHost;

    @Override
    public List<Consumable> getConsumables() {
        List<Consumable> consumablesList = new ArrayList<>();

        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        consumablesList = dbClient.view("_all_docs").includeDocs(true).query(Consumable.class);
        dbClient.shutdown();

        return consumablesList;
    }

    @Override
    public void createConsumable(String name, double price, List<IngredientUsed> ingredients) {
        Consumable consumable = new Consumable();
        consumable.set_id(UUID.randomUUID().toString());
        consumable.setName(name);
        consumable.setPriceOnMenu(price);
        consumable.setIngredientUsedList(ingredients);

        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        dbClient.save(consumable);
        dbClient.shutdown();
    }

    @Override
    public void deleteConsumable(String id) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));
        Consumable consumable = dbClient.find(Consumable.class, id);
        dbClient.remove(consumable);
        dbClient.shutdown();
    }

    @Override
    public void updateConsumable(Consumable consumable) {
        CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties(dbHost, dbName));

        Consumable consumableToBeUpdated = dbClient.find(Consumable.class, consumable.get_id());
        consumableToBeUpdated.setName(consumable.getName());
        consumableToBeUpdated.setPriceOnMenu(consumable.getPriceOnMenu());
        consumableToBeUpdated.setIngredientUsedList(consumable.getIngredientUsedList());

        dbClient.update(consumableToBeUpdated);
        dbClient.shutdown();
    }
}
