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

public class CSVReader {

    public static Set<Item> getItems() {
        List<String[]> allData = getAllData(new File(Constants.PRICECARD_PATH));
        Set<Item> items = new HashSet<>();
        
        for(Constants.ItemType type : Constants.ItemType.values()) {
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
    
    public static List<ItemCard> getItemCards() {
        List<ItemCard> itemCards = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.ITEMCARDS_PATH));
        
        for(String[] record : allData) {
            ItemCard itemCard = new ItemCard();
            
            switch(record[0]) {
                case "s":
                    itemCard.setType(Constants.CardType.Shipment);
                    break;
                case "o":
                    itemCard.setType(Constants.CardType.Order);
                    break;
                default:
                    System.err.println(Constants.ERROR_DATA_MSG);
            }
            itemCard.setCity(Constants.City.valueOf(record[1]));
            
            Set<Item> items = WarehouseService.getItems();
            Map<Item, Integer> itemMap = new HashMap<>();
            
            for(Item item : items) {
                switch(item.getType()) {
                    case Laptop:
                        itemMap.put(item, Integer.parseInt(record[2]));
                        break;
                    case Printer:
                        itemMap.put(item, Integer.parseInt(record[3]));
                        break;
                    case Table:
                        itemMap.put(item, Integer.parseInt(record[4]));
                        break;
                    default:
                        System.out.println(Constants.ERROR_DATA_MSG);
                }
            }
            itemCard.setItems(itemMap);
            itemCards.add(itemCard);
        }
        return itemCards;
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
