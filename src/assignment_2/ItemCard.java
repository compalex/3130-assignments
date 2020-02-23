package assignment_2;

import java.util.HashMap;
import java.util.Map;

public class ItemCard {
    private Constants.CardType type;
    private Constants.City city;
    private Map<Item, Integer> items;
    
    public Constants.CardType getType() {
        return type;
    }
    
    public void setType(Constants.CardType type) {
        this.type = type;
    }
    
    public Constants.City getCity() {
        return city;
    }
    
    public void setCity(Constants.City city) {
        this.city = city;
    }

    public Map<Item, Integer> getItems() {
        if(items == null) {
            items = new HashMap<>();
        }
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }
}
