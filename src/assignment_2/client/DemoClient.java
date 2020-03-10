package assignment_2.client;

import java.util.List;
import java.util.Map;
import assignment_2.Constants;
import assignment_2.model.AbstractItem;
import assignment_2.model.Card; 
import assignment_2.server.ItemProcessor;

/**
 * Assignment #2
 * The program reads in the data from CSV files and process it
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class DemoClient {
    
    public static void main(String[] args) {
        //getting a list of all cards from "itemCards.csv" file
        List<Card> cards = CardService.getCards();
        
        //launching a processor that acts as a server with a Database for Items
        ItemProcessor processor = ProcessorLauncher.launchAsyncProcessor();
        
        //sending cards 1by1 to the processor to handle them; printing out results
        for(Card card : cards) {
            CardService.printItemCard(card);  
            Map<AbstractItem, Integer> items = CardService.getItemsFromCard(card);
            switch(card.getType()) {
                case Shipment:
                    boolean isShipped = processor.processShipment(items, card.getCity());
                    if(!isShipped) {
                        System.err.println(Constants.SHIPMENT_ERROR_MSG);
                    }
                    break;
                case Order:
                    OrderResponse response = processor.processOrder(items, card.getCity());
                    if(response.isProcessed()) {
                        System.out.print(response.getDetails());
                        System.out.printf(Constants.ORDER_PRICE, response.getOrderPrice());
                    } else {
                        System.out.println(Constants.ORDER_UNFILLED_MSG);
                    }
                    break;
                default:
                    System.err.println(Constants.ERROR_DATA_MSG);
            }
            CardService.printWarehouse(card.getCity(), processor.getWarehouseState(card.getCity()));
            System.out.println(Constants.LINE_SEPARATOR);
        }
    }
}
