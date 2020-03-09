package model;

import java.util.HashMap;
import java.util.Map;

import assignment_2.Constants;

public class Warehouse {

    private Constants.City city;
    private Map<Item.Type, Integer> items;

    public Warehouse(Constants.City city) {
        this.city = city;
        items = new HashMap<>();
            
        for(Item.Type type : Item.Type.values()) {
            items.put(type, 0);
        }
    }
    
    public Constants.City getCity() {
        return city;
    }

    public void setCity(Constants.City city) {
        this.city = city;
    }

    public Map<Item.Type, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<Item.Type, Integer> items) {
        this.items = items;
    }
}
