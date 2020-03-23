package assignment_3.demoClient;

import java.util.List;
import assignment_3.model.Record;

/**
 * Assignment #3 - LINKED LISTS
 * The program reads in and processes the data using LinkedLists
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class DemoClient {
    
    public static void main(String[] args) {
        //Service processes Records from data.csv and sending requests to Processor
        RecordService service = new RecordService();
        
        List<Record> records = service.getRecords();
        
        for(Record record : records) {
            service.processRecord(record);            
        }
        
        service.printStockRemainder();
    }
}
