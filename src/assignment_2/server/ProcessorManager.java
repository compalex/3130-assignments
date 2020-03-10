package assignment_2.server;

import java.util.ArrayList;
import java.util.List;
import assignment_2.Constants.City;
import assignment_2.model.AbstractItem.Product;
import assignment_2.model.WarehouseItem;

public class ProcessorManager {
    private List<WarehouseItem> items;
    
    public ProcessorManager() {
        items = new ArrayList<>();
    }
    
    public List<WarehouseItem> getByType(Product product) {
        List<WarehouseItem> itemList = new ArrayList<>();
        
        for(WarehouseItem item : items) {
            if(item.getProduct() == product) {
                itemList.add(item);
            }
        }
        return itemList;
    }
    
    public List<WarehouseItem> getByCity(City city) {
        List<WarehouseItem> itemList = new ArrayList<>();
        
        for(WarehouseItem item : items) {
            if(item.getCity() == city) {
                itemList.add(item);
            }
        }
        return itemList;
    }
    
    public List<WarehouseItem> getByTypeAndCity(Product product, City city) {
        List<WarehouseItem> itemList = new ArrayList<>();
        
        for(WarehouseItem item : items) {
            if(item.getProduct() == product && item.getCity() == city) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    public boolean addItems(List<WarehouseItem> itemList) {
        if(itemList == null || itemList.isEmpty()) {
            return false;
        } else {
            return items.addAll(itemList);  
        }
    }
    
    public boolean removeItems(List<WarehouseItem> itemList) {
        if(itemList == null || itemList.isEmpty()) {
            return false;
        } else {
            return items.removeAll(itemList);  
        }
    }
}
