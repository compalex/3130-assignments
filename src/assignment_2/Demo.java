package assignment_2;

import java.util.List;
import model.Card;

/**
 * Assignment #2
 * The program reads in the data from CSV files and process it
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class Demo {
    
    public static void main(String[] args) {
        //getting the list of all cards from "itemCards.csv"
        List<Card> cards = CardService.getCards();
        
        //WarehouseManager stores Warehouses, Items, doing "lazy" initialization of them
        //Also it offers adding/removing of items in a particular Warehouse
        WarehouseManager manager = new WarehouseManager();

        //processing and printing
        for(Card card : cards) {
            CardService.printItemCard(card);
            CardService.processItemCard(card, manager);

        }
        System.out.println();
    }
}
