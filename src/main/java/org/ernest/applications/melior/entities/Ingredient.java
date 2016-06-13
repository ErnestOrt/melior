package org.ernest.applications.melior.entities;


public class Ingredient {

    private String _id;
    private String _rev;

    private String name;
    private double price;

    public Ingredient(){}

    public Ingredient(String id, String name, double price) {
        this._id = id;
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
