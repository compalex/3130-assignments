package assignment_2.model;

import java.util.HashMap;
import java.util.Map;

import assignment_2.Constants;

public class Warehouse {

    private Constants.City city;
    private Map<AbstractItem.Product, Integer> items;

    public Warehouse(Constants.City city) {
        this.city = city;
        items = new HashMap<>();
            
        for(AbstractItem.Product type : AbstractItem.Product.values()) {
            items.put(type, 0);
        }
    }
    
    public Constants.City getCity() {
        return city;
    }

    public void setCity(Constants.City city) {
        this.city = city;
    }

    public Map<AbstractItem.Product, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<AbstractItem.Product, Integer> items) {
        this.items = items;
    }
}
