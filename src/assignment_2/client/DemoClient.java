package assignment_2.demoClient;

import java.util.List;
import assignment_2.itemProcessor.ItemProcessor;
import assignment_2.itemProcessor.ProcessorLauncher;
import assignment_2.model.Card;

/**
 * Assignment #2
 * The program reads in the data from CSV files and process it
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class DemoClient {
    
    public static void main(String[] args) {
        //launching a processor that acts as a server with a Database for Items
        ItemProcessor processor = ProcessorLauncher.start();
        
        //getting a list of all cards from "itemCards.csv" file
        List<Card> cards = CardService.getCards();
        
        //sending cards 1by1 to the processor to handle them; printing out results
        for(Card card : cards) {
            CardService.processCard(card);
        }
    }
}
