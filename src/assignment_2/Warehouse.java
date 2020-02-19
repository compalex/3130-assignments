package assignment_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {
    private Constants.City city;
    private Map<Item, Integer> items;

    public Warehouse(Constants.City city, List<Double> prices) {
        this.city = city;
        items = new HashMap<>();
        
        for(int i = 0; i < Constants.ITEM_NUMBER; i++) {
            items.put(new Item(i + 1, prices.get(i)), 0);
        }
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

    public void addItems(int[] amounts) {
        this.items = items;
    }
}
