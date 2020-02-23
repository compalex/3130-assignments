package assignment_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Factory {
    private static Factory instance;
    private List<Warehouse> warehouses;
    private Set<Item> items;
    
    //Singleton
    public static Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }
        return instance;
    }
    
    public List<Warehouse> getWarehouses() {
        if(warehouses == null) {
            warehouses = new ArrayList<>();
        }
        if(warehouses.isEmpty()) {
            for(Constants.City city : Constants.City.values()) {
                warehouses.add(new Warehouse(city)); 
            }
        }
        return warehouses;   
    }
    
    public Set<Item> getItems() {
        if(items == null || items.isEmpty()) {
            items = CSVReader.getItems();
        }
        return items;
    }
}
