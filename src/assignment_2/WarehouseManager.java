package assignment_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import assignment_2.model.Item;
import assignment_2.model.Warehouse;
import assignment_2.model.Item.Type;

public class WarehouseManager {
    private List<Warehouse> warehouses;
    private Set<Item> items;
    
    public boolean addItem(Constants.City city, Item.Type type, int num) {
        Warehouse warehouse = getWarehouse(city);
        if(warehouse == null) {
            return false;
        } else {
            warehouse.getItems().put(type, warehouse.getItems().get(type) + num);
            return true;   
        }
    }
    
    public boolean removeItem(Constants.City city, Item.Type type, int num) {
        Warehouse warehouse = getWarehouse(city);
        if(warehouse == null) {
            return false;
        } else if(warehouse.getItems().get(type) < num) {
            return false;
        } else {
            warehouse.getItems().put(type, warehouse.getItems().get(type) - num);
            return true;
        }
    }
    
    public Warehouse getWarehouse(Constants.City city) {
        for(Warehouse warehouse : getWarehouses()) {
            if(warehouse.getCity() == city) {
                return warehouse;
            }
        } 
        return null;
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

    public double getPrice(Type type) {
        if(items == null || items.isEmpty()) {
            items = CSVReader.getItems();
        }
        for(Item item : items) {
            if(item.getType() == type) {
                return item.getPrice();
            }
        }
        return 0;
    }
}
