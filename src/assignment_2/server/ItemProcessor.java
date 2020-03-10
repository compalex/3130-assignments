package assignment_2.server;

import java.util.HashMap;
import java.util.Map;
import assignment_2.Constants;
import assignment_2.Constants.City;
import assignment_2.client.OrderResponse;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;

public class ItemProcessor {
    private static ItemProcessor instance;
    private ProcessorManager manager;
    
    private ItemProcessor() {
        manager = new ProcessorManager();
    }
    
    public static ItemProcessor getInstance() {
        if(instance == null) {
            instance = new ItemProcessor();
        }
        return instance;
    }
    
    public Map<Product, Integer> getWarehouseState(City city) {
        Map<Product, Integer> state = new HashMap<>();
        
        for(Product product : Product.values()) {
            state.put(product, manager.getByTypeAndCity(product, city).size());
        }
        return state;
    }

    public boolean processShipment(Map<AbstractItem, Integer> items, City city) {
        return ProcessorService.addItems(items, city, manager);
    }
    
    public OrderResponse processOrder(Map<AbstractItem, Integer> items, City city) {
        if(checkCurrentCity(items, city)) {
            return makeSimpleOrder(items, city);
        } else {
            Map<AbstractItem, Integer> lackingItems = getLackingItems(items, city);
            if(checkEverywhere(lackingItems)) {
                return makeMultiOrder(items, lackingItems, city);
            } else {
                return new OrderResponse();
            }
        }
    }
    
    public String showCurrentState(Constants.City city) {
        String state = city + " has ";
        state += "Laptops:" + manager.getByTypeAndCity(Product.Laptop, city).size() + " ";
        state += "Printers:" + manager.getByTypeAndCity(Product.Printer, city).size() + " ";
        state += "Tables:" + manager.getByTypeAndCity(Product.Table, city).size() + "\n";
        return state;
    }

    private OrderResponse makeSimpleOrder(Map<AbstractItem, Integer> items, City city) {
        OrderResponse response = new OrderResponse();
        double orderPrice = 0;
        
        for(AbstractItem item : items.keySet()) {
            boolean isRemoved = ProcessorService.removeItems(item, items.get(item), city, manager);
            if(isRemoved) {
                orderPrice += item.getPrice() * items.get(item);
            }
        }
        response.setOrderPrice(orderPrice);
        response.setProcessed(true);
        return response;
    }
    
    private OrderResponse makeMultiOrder(Map<AbstractItem, Integer> items, Map<AbstractItem, Integer> lackingItems,
            City city) {
        OrderResponse response = new OrderResponse();
        double orderPrice = 0;
        
        for(AbstractItem item : items.keySet()) {
            if(lackingItems.keySet().contains(item)) {
                City apprCity = ProcessorService.getAppropriateCity(item, manager);
                boolean isRemoved = ProcessorService.removeItems(
                        item, lackingItems.get(item), apprCity, manager);
                if(isRemoved) {
                    orderPrice += 1.1 * item.getPrice() * lackingItems.get(item);
                    response.addDetails(String.format(Constants.INTERCHANGE_FORMAT, 
                            lackingItems.get(item), item.getProduct(), city, apprCity));
                    response.addDetails(showCurrentState(apprCity));
                } 
                isRemoved = ProcessorService.removeItems(
                        item, items.get(item) - lackingItems.get(item), city, manager);
                if(isRemoved) {
                    orderPrice += item.getPrice() * items.get(item) - lackingItems.get(item);
                } 
            } else {
                boolean isRemoved = ProcessorService.removeItems(item, items.get(item), city, manager);
                if(isRemoved) {
                    orderPrice += item.getPrice() * items.get(item);
                } 
            }
        }
        response.setOrderPrice(orderPrice);
        response.setProcessed(true);
        return response;
    }

    private boolean checkCurrentCity(Map<AbstractItem, Integer> items, City city) {
        for(AbstractItem item : items.keySet()) {
            if(items.get(item) > manager.getByTypeAndCity(item.getProduct(), city).size()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkEverywhere(Map<AbstractItem, Integer> lackingItems) {
        for(AbstractItem item : lackingItems.keySet()) {
            boolean isLacking = true;
            
            for(City city : City.values()) {
                if(lackingItems.get(item) < manager.getByTypeAndCity(item.getProduct(), city).size()) {
                    isLacking = false;
                }
            }
            if(isLacking) {
                return false;
            }
            
        }
        return true;
    }
    
    private Map<AbstractItem, Integer> getLackingItems(Map<AbstractItem, Integer> items, City city) {
        Map<AbstractItem, Integer> lackingItems = new HashMap<>();

        for(AbstractItem item : items.keySet()) {
            int difference = items.get(item) - 
                    manager.getByTypeAndCity(item.getProduct(), city).size();
            if(difference > 0) {
                lackingItems.put(item, difference);
            }
        }
        return lackingItems;
    }
}
