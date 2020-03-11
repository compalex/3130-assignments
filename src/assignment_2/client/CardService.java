package assignment_2.demoClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import assignment_2.CSVReader;
import assignment_2.Constants;
import assignment_2.Constants.City;
import assignment_2.itemProcessor.ItemProcessor;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;
import assignment_2.model.Card;

public class CardService {
    private static Map<Product, Double> prices;

    public static void processCard(Card card) {
        CardService.printItemCard(card);  
        Map<AbstractItem, Integer> items = CardService.getItemsFromCard(card);
        ItemProcessor processor = ItemProcessor.getInstance();
        switch(card.getType()) {
            case Shipment:
                boolean isSuccess = processor.processShipment(items, card.getCity());
                if(isSuccess) {
                    CardService.printWarehouse(card.getCity(), processor.getWarehouseState(card.getCity()));
                } else {
                    System.err.println(Constants.SHIPMENT_ERROR_MSG);
                }
                break;
            case Order:
                OrderResponse response = processor.processOrder(items, card.getCity());
                if(response.isProcessed()) {
                    System.out.print(response.getDetails());
                    CardService.printWarehouse(card.getCity(), processor.getWarehouseState(card.getCity()));
                    System.out.printf(Constants.ORDER_PRICE, response.getOrderPrice());
                } else {
                    System.out.println(Constants.ORDER_UNFILLED_MSG);
                }
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);
        }
        System.out.println(Constants.LINE_SEPARATOR);
    }
    
    public static List<Card> getCards() {
        List<Card> itemCards = CSVReader.getCards();   
        if(itemCards.isEmpty()) {
            System.err.println(Constants.ERROR_DATA_MSG);
        } 
        return itemCards;
    }  

    public static void printItemCard(Card card) {
        if(card == null) {
            System.err.println(Constants.ERROR_DATA_MSG);
            return;
        }
        String line = Constants.PRINT_CARD;
        line += card.getType() + " " + card.getCity();
        Map<Product, Integer> items = card.getItems();
        line += String.format(Constants.PROD_FORMAT, Product.Laptop, items.get(Product.Laptop));
        line += String.format(Constants.PROD_FORMAT, Product.Printer, items.get(Product.Printer));
        line += String.format(Constants.PROD_FORMAT, Product.Table, items.get(Product.Table));
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
        String line = Constants.PRINT_WAREHOUSE + city;
        line += String.format(Constants.PROD_FORMAT, Product.Laptop, items.get(Product.Laptop));
        line += String.format(Constants.PROD_FORMAT, Product.Printer, items.get(Product.Printer));
        line += String.format(Constants.PROD_FORMAT, Product.Table, items.get(Product.Table));
        System.out.println(line);
    }
}
