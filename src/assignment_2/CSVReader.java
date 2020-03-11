package assignment_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import assignment_2.model.AbstractItem;
import assignment_2.model.AbstractItem.Product;
import assignment_2.model.Card;

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
            Map<AbstractItem.Product, Integer> itemMap = new HashMap<>();
            itemMap.put(AbstractItem.Product.Laptop, Integer.parseInt(record[2]));
            itemMap.put(AbstractItem.Product.Printer, Integer.parseInt(record[3]));
            itemMap.put(AbstractItem.Product.Table, Integer.parseInt(record[4]));
            card.setItems(itemMap);
            cards.add(card);
        }
        return cards;
    }
    
    public static Map<Product, Double> getPrices() {
        List<String[]> allData = getAllData(new File(Constants.PRICECARD_PATH));
        Map<Product, Double> prices = new HashMap<>();
        
        for(Product product : AbstractItem.Product.values()) {
            switch(product) {
                case Laptop:
                    prices.put(product, Double.parseDouble(allData.get(0)[0]));
                    break;
                case Printer:
                    prices.put(product, Double.parseDouble(allData.get(0)[1]));
                    break;
                case Table:
                    prices.put(product, Double.parseDouble(allData.get(0)[2]));
                    break;
                default:
                    System.out.println(Constants.ERROR_DATA_MSG);
            }
        }
        return prices;
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
