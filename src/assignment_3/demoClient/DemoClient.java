package assignment_3.demoClient;

import java.util.List;
import assignment_3.model.Record;
import assignment_3.widgetPocessor.IWidgetProcessor;
import assignment_3.widgetPocessor.ProcessorLauncher;

/**
 * Assignment #3 - LINKED LISTS
 * The program reads in and processes the data using LinkedLists
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class DemoClient {
    
    public static void main(String[] args) {
        /**
         * Processor is a separate thread with database(manager) and all processing logic.
         * We're launching it here, but considering it to be working as by default.
         * There's the only 1 Processor, which can have a lot of clients.
         * Processor and DemoClient are asynchronous threads.
         */
        IWidgetProcessor processor = ProcessorLauncher.launchAsyncProcessor();
        
        //Service processes Records from data.csv and sending requests to Processor
        RecordService service = new RecordService(processor);
        
        List<Record> records = service.getRecords();
        
        for(Record record : records) {
            service.processRecord(record);            
        }
        
        service.printRemainingWidgets();
    }
}
