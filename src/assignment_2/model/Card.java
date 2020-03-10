package assignment_2.model;

import java.util.HashMap;
import java.util.Map;
import assignment_2.Constants;

public class Card {
    
    public enum Type {
        Shipment,
        Order
    }
    
    private Type type;
    private Constants.City city;
    private Map<AbstractItem.Product, Integer> items;
    
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public Constants.City getCity() {
        return city;
    }
    
    public void setCity(Constants.City city) {
        this.city = city;
    }

    public Map<AbstractItem.Product, Integer> getItems() {
        if(items == null) {
            items = new HashMap<>();
        }
        return items;
    }

    public void setItems(Map<AbstractItem.Product, Integer> items) {
        this.items = items;
    }
}
