package assignment_2;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import assignment_2.Constants.City;
import model.Card;
import model.Item;
import model.Warehouse;

public class CardService {

    public static List<Card> getCards() {
        List<Card> itemCards = CSVReader.getCards();   
        if(itemCards == null || itemCards.isEmpty()) {
            System.out.println(Constants.ERROR_DATA_MSG);
        } 
        return itemCards;
    }  

    public static void processItemCard(Card card, WarehouseManager manager) {
        switch(card.getType()) {
            case Shipment:
                processShipmentCard(card, manager);
                WarehouseService.printWarehouse(card.getCity(), manager);
                break;
            case Order:
                double orderPrice = processOrderCard(card, manager);
                WarehouseService.printWarehouse(card.getCity(), manager);
                if(orderPrice > 0) {
                    System.out.println("Price of Order: " + orderPrice + "$");
                }
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);
        }    
        System.out.println(Constants.LINE_SEPARATOR);
    }

    private static void processShipmentCard(Card itemCard, WarehouseManager manager) {
        for(Item.Type type : itemCard.getItems().keySet()) {
            boolean success = manager.addItem(itemCard.getCity(), type, itemCard.getItems().get(type));
            if(!success) {
                System.out.println(Constants.ERROR_DATA_MSG);
            }
        }
    }
    
    private static double processOrderCard(Card card, WarehouseManager manager) {
        double orderPrice = 0;
        //check if the city has the all necessary items
        boolean isEnough = checkCurrentCity(card, manager);
        
        if(isEnough) {
            for(Item.Type type : card.getItems().keySet()) {
                int amount = card.getItems().get(type);
                boolean isUpdated = manager.removeItem(card.getCity(), type, amount);
                if(isUpdated) {
                    orderPrice += manager.getPrice(type) * amount;
                } else {
                    System.err.println(Constants.ERROR_DATA_MSG);
                }
            }
            return orderPrice;
        } else {
            Set<Item.Type> lackingItems = getLackingItems(card, manager);
            //check for lacking items in other cities
            isEnough = checkOtherCities(lackingItems, card, manager);
            if(isEnough) {
                for(Item.Type type : card.getItems().keySet()) {
                    if(lackingItems.contains(type)) {
                        int amountInStock = manager.getWarehouse(card.getCity()).getItems().get(type);
                        int amountToForward = card.getItems().get(type) - amountInStock;
                        City otherCity = getAppropriateCity(type, manager);
                        boolean isUpdated = manager.removeItem(card.getCity(), type, amountInStock);
                        if(isUpdated) {
                            orderPrice += manager.getPrice(type) * amountInStock;
                        } else {
                            System.out.println(Constants.ERROR_DATA_MSG);
                        }
                        isUpdated = manager.removeItem(otherCity, type, amountToForward);
                        if(isUpdated) {
                            orderPrice += 1.1 * manager.getPrice(type) * amountToForward;
                            System.out.println(amountToForward + " of item " + type + " shipped from " + otherCity + " to " + card.getCity() + " at a 10% surcharge");
                            WarehouseService.printWarehouse(otherCity, manager);
                        } else {
                            System.out.println(Constants.ERROR_DATA_MSG);
                        }
                    } else {
                        int amount = card.getItems().get(type);
                        boolean isUpdated = manager.removeItem(card.getCity(), type, amount);
                        if(isUpdated) {
                            orderPrice += manager.getPrice(type) * amount;
                        } else {
                            System.err.println(Constants.ERROR_DATA_MSG);
                        }
                    }
                }
                return orderPrice;
            } else {
                System.out.println(Constants.ORDER_UNFILLED);
                return 0;
            }
        }
    }


    private static boolean checkCurrentCity(Card card, WarehouseManager manager) {
        Map<Item.Type, Integer> items = manager.getWarehouse(card.getCity()).getItems();
        
        for(Item.Type type : items.keySet()) {
            if(card.getItems().get(type) > items.get(type)) {
                return false;
            }
        }
        return true;
    }
    
    private static Set<Item.Type> getLackingItems(Card card, WarehouseManager manager) {
        Map<Item.Type, Integer> items = manager.getWarehouse(card.getCity()).getItems();
        Set<Item.Type> lackingItems = new HashSet<>();

        for(Item.Type type : items.keySet()) {
            if(card.getItems().get(type) > items.get(type)) {
                lackingItems.add(type);
            }
        }
        return lackingItems;
    }
    
    private static boolean checkOtherCities(Set<Item.Type> lackingItems, 
            Card card, WarehouseManager manager) {
        List<Warehouse> warehouses = manager.getWarehouses();
        
        for(Item.Type type : lackingItems) {
            boolean isLacking = true;
            
            for(Warehouse warehouse : warehouses) {
                if(card.getItems().get(type) - manager.getWarehouse(card.getCity()).getItems().get(type) < warehouse.getItems().get(type)) {
                    isLacking = false;
                }
            }
            if(isLacking) {
                return false;
            }
            
        }
        return true;
    }

    public static City getAppropriateCity(Item.Type type, WarehouseManager manager) {
        City appropriateCity = null;
        int maxQuantity = 0;
        
        for(Warehouse warehouse : manager.getWarehouses()) {
            if(warehouse.getItems().get(type) > maxQuantity) {
                appropriateCity = warehouse.getCity();
                maxQuantity = warehouse.getItems().get(type);
            }
        }
        return appropriateCity;
    }
    
    public static void printItemCard(Card itemCard) {
        if(itemCard == null) {
            return;
        }
        String line = "Processing the card: '";
        line += itemCard.getType() + " " + itemCard.getCity();
        
        line += " " + Item.Type.Laptop + ":" + itemCard.getItems().get(Item.Type.Laptop);
        line += " " + Item.Type.Printer + ":" + itemCard.getItems().get(Item.Type.Printer);
        line += " " + Item.Type.Table + ":" + itemCard.getItems().get(Item.Type.Table);
        
        line += "'";
        System.out.println(line);   
    }
}
