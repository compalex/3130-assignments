package assignment_2;

import java.util.List;
import java.util.Map;

/**
 * Assignment #2
 * The program reads in the data from CSV file and process it
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class Demo {
    
    public static void main(String[] args) {
        //Creating the list of 5 Warehouses with 3 Items in each
        //Each Item has a price from "priceCard.csv" 
        List<Warehouse> warehouses = WarehouseService.getWarehousesWithItems();
        
        //getting the list of all cards from "itemCards.csv" 
        List<ItemCard> itemCards = CardService.getItemCards();
        
        //processing and printing
        for(ItemCard itemCard : itemCards) {
            CardService.printItemCard(itemCard);
            CardService.processItemCard(itemCard);
            WarehouseService.printWarehouse(WarehouseService.getWarehouse(itemCard.getCity()));
            System.out.println(Constants.LINE_SEPARATOR);
        }
        System.out.println();
        
        //printWarehouses(warehouses);
        
        //1by1 each card gives response to main
        
        //load Prices
    }
    
    public static void printWarehouses(List<Warehouse> warehouses) {
        for(Warehouse w : warehouses) {
            System.out.println(w.getCity());
            Map<Item, Integer> items = w.getItems();
            
            for(Item item : w.getItems().keySet()) {
                System.out.println(item.getType() + " price: " + item.getPrice() + " num: " + items.get(item));
            }
            System.out.println();
        }
    }
}
