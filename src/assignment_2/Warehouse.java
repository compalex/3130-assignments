package assignment_2;

import java.util.Map;

public class Warehouse {
    private Constants.City city;
    private Map<Item, Integer> items;

    public Warehouse(Constants.City city) {
        this.city = city;
    }
    
    public Constants.City getCity() {
        return city;
    }

    public void setCity(Constants.City city) {
        this.city = city;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }
}
