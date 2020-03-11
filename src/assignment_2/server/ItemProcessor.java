package assignment_2.itemProcessor;

import java.util.Map;
import assignment_2.Constants.City;
import assignment_2.demoClient.OrderResponse;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;

public class ItemProcessor {
    private static volatile ItemProcessor instance;
    
    public static ItemProcessor getInstance() {
        if(instance == null) {
            instance = new ItemProcessor();
        }
        return instance;
    }
    
    public Map<Product, Integer> getWarehouseState(City city) {
        return ItemService.getState(city);
    }

    public synchronized boolean processShipment(Map<AbstractItem, Integer> items, City city) {
        return ItemService.addItems(items, city);
    }
    
    public synchronized OrderResponse processOrder(Map<AbstractItem, Integer> items, City city) {
        return ItemService.processOrder(items, city);
    }
}
