package assignment_2;

import java.util.List;
import java.util.Map;

public class CardService {

    public static List<ItemCard> getItemCards() {
        List<ItemCard> itemCards = CSVReader.getItemCards();   
        if(itemCards == null || itemCards.isEmpty()) {
            return null;
        } else {
            return itemCards;
        }
    }  

    public static void processItemCard(ItemCard itemCard) {
        switch(itemCard.getType()) {
            case Shipment:
                processShipmentCard(itemCard);
                break;
            case Order:
                processOrderCard(itemCard);
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);
        }    
    }

    private static void processShipmentCard(ItemCard itemCard) {
        Warehouse warehouse = WarehouseService.getWarehouse(itemCard.getCity());
        Map<Item, Integer> itemMap = warehouse.getItems();
        
        for(Item item : itemMap.keySet()) {
            itemMap.put(item, itemMap.get(item) + itemCard.getItems().get(item));
        }
    }
    
    private static void processOrderCard(ItemCard itemCard) {
        Warehouse warehouse = WarehouseService.getWarehouse(itemCard.getCity());
        Map<Item, Integer> itemMap = warehouse.getItems();
        
        for(Item item : itemMap.keySet()) {
            if(itemMap.get(item) >= itemCard.getItems().get(item)) {
                itemMap.put(item, itemMap.get(item) - itemCard.getItems().get(item));
            } else {
                System.out.println("no place");
            }
        }
    }

    public static void printItemCard(ItemCard itemCard) {
        if(itemCard == null) {
            return;
        }
        String line = "Processing the card: '";
        line += itemCard.getType() + " " + itemCard.getCity();
        
        for(Item item : itemCard.getItems().keySet()) {
                line += " " + item.getType() + ":" + itemCard.getItems().get(item);
        }
        line += "'";
        System.out.println(line);   
    }
}
