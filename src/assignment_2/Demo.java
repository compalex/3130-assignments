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
        //getting the list of prices from "priceCard.csv"
        List<Double> prices = CardService.getPrices();
        //creating the list of 5 warehouses with 3 items in each
        List<Warehouse> warehouses = WarehouseService.getInstance().getWarehouses(prices);
        //getting the list of all cards from "itemCard.csv" 
        List<ItemCard> itemCards = CardService.getItemCards();
        
        //processing and printing of itemCards
        for(ItemCard itemCard : itemCards) {
            System.out.println(itemCard);
            CardService.processItemCard(itemCard);
            
        }
        System.out.println();
        
        printWarehouses(warehouses);
        
        //1by1 each card gives response to main
        
        //load Prices
    }
    
    public static void printWarehouses(List<Warehouse> warehouses) {
        for(Warehouse w : warehouses) {
            System.out.println(w.getCity());
            Map<Item, Integer> items = w.getItems();
            
            for(int i = 0; i < Constants.ITEM_NUMBER; i++) {
                for(Item item : w.getItems().keySet()) {
                    if(item.getIdNum() == i+1) {
                        System.out.println(item.getIdNum() + " price: " + item.getPrice() + " num: " + items.get(item));
                    }
                }
                
            }
            System.out.println();
        }
    }
}
