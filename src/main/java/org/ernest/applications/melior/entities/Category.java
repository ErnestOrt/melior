package org.ernest.applications.melior.entities;


import java.util.ArrayList;
import java.util.List;

public class Category {
    private String _id;
    private String _rev;

    private String name;
    private List<String> consumablesIds;

    public Category(){
        consumablesIds = new ArrayList<>();
    }

    public Category(String id, String name, List<String> consumablesIds) {
        this._id = id;
        this.name = name;
        this.consumablesIds = consumablesIds;
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

    public List<String> getConsumablesIds() {
        return consumablesIds;
    }

    public void setConsumablesIds(List<String> consumablesIds) {
        this.consumablesIds = consumablesIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
