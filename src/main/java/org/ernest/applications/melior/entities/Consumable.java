package org.ernest.applications.melior.entities;


import java.util.ArrayList;
import java.util.List;

public class Consumable {
    private String _id;
    private String _rev;

    private String name;
    private double priceOnMenu;
    private double cost;
    private List<IngredientUsed> ingredientUsedList;

    public Consumable(){
        ingredientUsedList = new ArrayList<>();
    }

    public Consumable(String _id, String name, double priceOnMenu, List<IngredientUsed> ingredientUsedList) {
        this._id = _id;
        this.name = name;
        this.priceOnMenu = priceOnMenu;
        this.ingredientUsedList = ingredientUsedList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceOnMenu() {
        return priceOnMenu;
    }

    public void setPriceOnMenu(double priceOnMenu) {
        this.priceOnMenu = priceOnMenu;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<IngredientUsed> getIngredientUsedList() {
        return ingredientUsedList;
    }

    public void setIngredientUsedList(List<IngredientUsed> ingredientUsedList) {
        this.ingredientUsedList = ingredientUsedList;
    }
}
