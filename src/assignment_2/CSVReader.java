package assignment_2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    
    public static List<Double> getPrices() {
        List<String[]> allData = getAllData(new File(Constants.PRICECARD_PATH));
        List<Double> prices = new ArrayList<>();
        
        for(String data : allData.get(0)) {
            prices.add(Double.parseDouble(data));
        }
        return prices;
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
            itemCard.setAmounts(new ArrayList<>());
            
            for(int i = 1; i <= Constants.ITEM_NUMBER; i++) {
                itemCard.getAmounts().add(Integer.parseInt(record[i + 1]));
            }
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
