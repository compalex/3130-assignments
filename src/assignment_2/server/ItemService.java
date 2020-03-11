package assignment_2.itemProcessor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import assignment_2.Constants;
import assignment_2.Constants.City;
import assignment_2.demoClient.OrderResponse;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;
import assignment_2.model.WarehouseItem;

public class ItemService {

    public static Map<Product, Integer> getState(City city) {
        Map<Product, Integer> state = new HashMap<>();
        
        for(Product product : Product.values()) {
            state.put(product, ItemManager.getInstance().getByTypeAndCity(product, city).size());
        }
        return state;
    }
    
    public static boolean addItems(Map<AbstractItem, Integer> items, City city) {
        List<WarehouseItem> listToAdd = new ArrayList<>();
        
        for(AbstractItem item : items.keySet()) {
            listToAdd.addAll(createWarehouseItems(item, items.get(item), city));
        }
        return ItemManager.getInstance().addItems(listToAdd);
    }

    public static OrderResponse processOrder(Map<AbstractItem, Integer> items, City city) {
        OrderResponse response = new OrderResponse();
        if(checkAvailability(items, city)) {
            response.setProcessed(true);
            List<WarehouseItem> itemsToRemove = prepareList(items, city, response);
            response.setOrderPrice(calculatePrice(itemsToRemove, city));
            ItemManager.getInstance().removeItems(itemsToRemove);
        } else {
            response.setProcessed(false);
        }
        return response;
    }

    private static boolean checkAvailability(Map<AbstractItem, Integer> items, City city) {
        ItemManager manager = ItemManager.getInstance();
        
        for(AbstractItem item : items.keySet()) {
            int difference = items.get(item) - manager.getByTypeAndCity(item.getProduct(), city).size();
            if(difference > 0) {
                City assistCity = getAuxCity(item, city);
                if(difference > manager.getByTypeAndCity(item.getProduct(), assistCity).size()) {
                    return false; 
                }
            }
        }
        return true;
    }

    private static List<WarehouseItem> prepareList(Map<AbstractItem, Integer> items, City city, OrderResponse response) {
        List<WarehouseItem> itemsToRemove = new ArrayList<>();
        ItemManager manager = ItemManager.getInstance();
        
        for(AbstractItem item : items.keySet()) {
            List<WarehouseItem> itemsInStock = manager.getByTypeAndCity(item.getProduct(), city);
            int difference = items.get(item) - itemsInStock.size();
            if(difference <= 0) {
                for(int i = 0; i < items.get(item); i++) {
                    itemsToRemove.add(itemsInStock.get(i));
                }
            } else {
                for(int i = 0; i < itemsInStock.size(); i++) {
                    itemsToRemove.add(itemsInStock.get(i));
                }
                City auxCity = getAuxCity(item, city);
                List<WarehouseItem> itemsInAuxStock = manager.getByTypeAndCity(item.getProduct(), auxCity);
                for(int i = 0; i < difference; i++) {
                    itemsToRemove.add(itemsInAuxStock.get(i));
                }
                response.addDetails(String.format(Constants.CHANGE_FORMAT, difference, item.getProduct(), auxCity, city));
                response.addDetails(showCurrentState(auxCity, item.getProduct(), difference));
            }
        }
        return itemsToRemove;
    }
    
    private static double calculatePrice(List<WarehouseItem> itemsToRemove, City city) {
        double orderPrice = 0;
        
        for(WarehouseItem item : itemsToRemove) {
            if(item.getCity() == city) {
                orderPrice += item.getPrice();
            } else {
                orderPrice += item.getPrice() * 1.1;
            }
        }
        return orderPrice;
    }
    
    private static City getAuxCity(AbstractItem item, City cityToElide) {
        ItemManager manager = ItemManager.getInstance();
        Map<City, Integer> stockMap = new HashMap<>();
        
        for(City city : City.values()) {
            if(city != cityToElide)
            stockMap.put(city, manager.getByTypeAndCity(item.getProduct(), city).size());
        }
        Map<City, Integer> sortedByValues = new TreeMap<City, Integer>(new Comparator<City>() {
            
            @Override
            public int compare(City a, City b) { 
                return stockMap.get(b) - stockMap.get(a); 
            } 
        });  
        sortedByValues.putAll(stockMap); 
        return (City)sortedByValues.keySet().toArray()[0];
    }
    
    private static List<WarehouseItem> createWarehouseItems(AbstractItem item, Integer amount, City city) {
        List<WarehouseItem> items = new ArrayList<>();
        
        for(int i = 0; i < amount; i++) {
            items.add(new WarehouseItem(item.getProduct(), item.getPrice(), city));
        }
        return items;
    }    
    
    private static String showCurrentState(City city, Product corrProduct, int correction) {
        Map<Product, Integer> stateMap = getState(city);
        String state = Constants.PRINT_WAREHOUSE + city;
        for(Product product : Product.values()) {
            int amount = stateMap.get(product);
            if(product == corrProduct) {
                amount -= correction;
            }
            state += String.format(Constants.PROD_FORMAT, product, amount);
        }
        state += "\n";
        return state;
    }
}
