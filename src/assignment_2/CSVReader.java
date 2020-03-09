package assignment_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Card;
import model.Item;

public class CSVReader {

    public static List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.ITEMCARDS_PATH));
        
        for(String[] record : allData) {
            Card card = new Card();
            
            switch(record[0]) {
                case "s":
                    card.setType(Card.Type.Shipment);
                    break;
                case "o":
                    card.setType(Card.Type.Order);
                    break;
                default:
                    System.err.println(Constants.ERROR_DATA_MSG);
            }
            card.setCity(Constants.City.valueOf(record[1]));
            Map<Item.Type, Integer> itemMap = new HashMap<>();
            itemMap.put(Item.Type.Laptop, Integer.parseInt(record[2]));
            itemMap.put(Item.Type.Printer, Integer.parseInt(record[3]));
            itemMap.put(Item.Type.Table, Integer.parseInt(record[4]));
            card.setItems(itemMap);
            cards.add(card);
        }
        return cards;
    }
    
    public static Set<Item> getItems() {
        List<String[]> allData = getAllData(new File(Constants.PRICECARD_PATH));
        Set<Item> items = new HashSet<>();
        
        for(Item.Type type : Item.Type.values()) {
            switch(type) {
                case Laptop:
                    items.add(new Item(type, Double.parseDouble(allData.get(0)[0])));
                    break;
                case Printer:
                    items.add(new Item(type, Double.parseDouble(allData.get(0)[1])));
                    break;
                case Table:
                    items.add(new Item(type, Double.parseDouble(allData.get(0)[2])));
                    break;
                default:
                    System.out.println(Constants.ERROR_DATA_MSG);
            }
        }
        return items;
    }
    
    private static List<String[]> getAllData(File file) {
        List<String[]> records = new ArrayList<String[]>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {  
            String line = br.readLine();
            
            while((line = br.readLine()) != null) {
                String[] values = line.split(Constants.SEPARATOR);
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
