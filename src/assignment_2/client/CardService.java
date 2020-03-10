package assignment_2.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import assignment_2.CSVReader;
import assignment_2.Constants;
import assignment_2.Constants.City;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;
import assignment_2.model.Card;

public class CardService {
    private static Map<Product, Double> prices;
    
    public static List<Card> getCards() {
        List<Card> itemCards = CSVReader.getCards();   
        if(itemCards.isEmpty()) {
            System.err.println(Constants.ERROR_DATA_MSG);
        } 
        return itemCards;
    }  

    public static void printItemCard(Card itemCard) {
        if(itemCard == null) {
            return;
        }
        String line = "Processing the card: '";
        line += itemCard.getType() + " " + itemCard.getCity();
        
        line += " " + AbstractItem.Product.Laptop + ":" + itemCard.getItems().get(AbstractItem.Product.Laptop);
        line += " " + AbstractItem.Product.Printer + ":" + itemCard.getItems().get(AbstractItem.Product.Printer);
        line += " " + AbstractItem.Product.Table + ":" + itemCard.getItems().get(AbstractItem.Product.Table);
        
        line += "'";
        System.out.println(line);   
    }
    
    public static Map<AbstractItem, Integer> getItemsFromCard(Card card) {
        Map<Product, Integer> cardItems = card.getItems();
        Map<AbstractItem, Integer> items = new HashMap<>();
        
        for(Product product : cardItems.keySet()) {
            items.put(new AbstractItem(product, getPrice(product)), cardItems.get(product));
        }
        return items;
    }
    
    public static double getPrice(Product product) {
        if(prices == null || prices.isEmpty()) {
            prices = CSVReader.getPrices();
        }
        return prices.get(product);
    }
    
    public static void printWarehouse(City city, Map<Product, Integer> items) {
        String line = "Warehouse at " + city + " has";
        line += " " + AbstractItem.Product.Laptop + ":" + items.get(AbstractItem.Product.Laptop);
        line += " " + AbstractItem.Product.Printer + ":" + items.get(AbstractItem.Product.Printer);
        line += " " + AbstractItem.Product.Table + ":" + items.get(AbstractItem.Product.Table);
        System.out.println(line);
    }
}
