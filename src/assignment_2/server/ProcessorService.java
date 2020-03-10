package assignment_2.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import assignment_2.Constants.City;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;
import assignment_2.model.WarehouseItem;

public class ProcessorService {

    public static boolean addItems(Map<AbstractItem, Integer> items, City city, ProcessorManager manager) {
        List<WarehouseItem> itemList = new ArrayList<>();
        
        for(AbstractItem item : items.keySet()) {
            for(int i = 0; i < items.get(item); i++) {
                itemList.add(new WarehouseItem(item.getProduct(), item.getPrice(), city));
            }
        }
        return manager.addItems(itemList);
    }
    
    public static boolean removeItems(AbstractItem item, int amount, City city, ProcessorManager manager) {
        List<WarehouseItem> listToRemove = new ArrayList<>();
        List<WarehouseItem> listFromDB = manager.getByTypeAndCity(item.getProduct(), city);
        
        for(int i = 0; i < amount; i++) {
            for(WarehouseItem WItem : listFromDB) {
                if(item.getProduct() == WItem.getProduct()) {
                    listToRemove.add(WItem);
                }
            }
        }
        return manager.removeItems(listToRemove);
    }
    
    public static City getAppropriateCity(AbstractItem item, ProcessorManager manager) {
        List<WarehouseItem> items = manager.getByType(item.getProduct());
        Map<City, Integer> counter = new HashMap<>();
        
        for(WarehouseItem WItem : items) {
            if(counter.get(WItem.getCity()) == null) {
                counter.put(WItem.getCity(), 0);
            } else {
                counter.put(WItem.getCity(), counter.get(WItem.getCity()) + 1);
            }
        }
        City apprCity = null;
        int maxQuantity = 0;
        
        for(City city : counter.keySet()) {
            if(counter.get(city) > maxQuantity) {
                apprCity = city;
                maxQuantity = counter.get(city);
            }
        }
        return apprCity;
    }
}
