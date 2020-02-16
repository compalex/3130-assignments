package assignment_2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static double[] getPrices() {
        List<String[]> allData = getAllData(new File(Constants.PRICECARD_PATH));
        return new double[] {Double.parseDouble(allData.get(0)[0]),
                Double.parseDouble(allData.get(0)[1]), Double.parseDouble(allData.get(0)[2])};
    }
    
    public static List<ItemCard> getItemCards() {
        List<ItemCard> itemCards = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.ITEMCARDS_PATH));
        
        for(String[] record : allData) {
            ItemCard itemCard = new ItemCard();
            
            switch(record[0]) {
                case "s":
                    itemCard.setType(Constants.ItemType.Shipment);
                    break;
                case "o":
                    itemCard.setType(Constants.ItemType.Order);
                    break;
                default:
                    System.err.println(Constants.ERROR_DATA_MSG);
            }
            itemCard.setCity(record[1]);
            itemCard.setAmounts(Integer.parseInt(record[2]), 
                    Integer.parseInt(record[3]), Integer.parseInt(record[4]));
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
