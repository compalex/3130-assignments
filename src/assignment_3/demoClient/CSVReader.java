package assignment_3.demoClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import assignment_3.Constants;
import assignment_3.model.PromoRecord;
import assignment_3.model.ReceiptRecord;
import assignment_3.model.Record;
import assignment_3.model.SalesRecord;

public class CSVReader {
    
    public static List<Record> getRecords() {
        List<Record> records = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.DATA_PATH));
        
        for(String[] data : allData) {
            Record record = null;    
            switch(data[0]) {
                case "R":
                    record = new ReceiptRecord(Integer.parseInt(data[1]), Double.parseDouble(data[2]));
                    break;
                case "S":
                    record = new SalesRecord(Integer.parseInt(data[1]));
                    break;
                case "P":
                    record = new PromoRecord(Double.parseDouble(data[1].replace("%", "")));
                    break;
                default:
                    System.err.println(Constants.ERROR_DATA_MSG);
            }
            records.add(record);
        }
        return records;
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
