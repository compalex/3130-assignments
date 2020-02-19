package assignment_2;

import java.util.List;
import java.util.Map;

public class CardService {

    public static List<ItemCard> getItemCards() {
        return CSVReader.getItemCards();        
    }  

    public static void processItemCard(ItemCard itemCard) {
        switch(itemCard.getType()) {
            case Shipment:
                CardService.processShipmentCard(itemCard);
                break;
            case Order:
                //bla
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);
        }    
    }

    private static void processShipmentCard(ItemCard itemCard) {
        Warehouse warehouse = 
                WarehouseService.getInstance().getWarehouse(itemCard.getCity());
        Map<Item, Integer> itemMap = warehouse.getItems();
        
        for(Item item : itemMap.keySet()) {
            itemMap.put(item, itemMap.get(item) + itemCard.getAmounts().get(item.getIdNum() - 1));
        }
    }
    
    private static void processOrderCard(ItemCard itemCard) {
        Warehouse warehouse = 
                WarehouseService.getInstance().getWarehouse(itemCard.getCity());
        Map<Item, Integer> itemMap = warehouse.getItems();
        
        
        for(Item item : itemMap.keySet()) {
            if(itemCard.getAmounts().get(item.getIdNum() - 1) <= itemMap.get(item)) {
                itemMap.put(item, itemMap.get(item) + itemCard.getAmounts().get(item.getIdNum() - 1));
            } else {
                
            }
        }
    }

    public static List<Double> getPrices() {
        return CSVReader.getPrices();
    }
}
