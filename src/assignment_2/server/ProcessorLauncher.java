package assignment_2.client;

import java.util.Set;
import assignment_2.CSVReader;
import assignment_2.model.AbstractItem;
import assignment_2.server.ItemProcessor;

public class ProcessorLauncher {

    public static ItemProcessor launchAsyncProcessor() {
        //Set<AbstractItem> items = CSVReader.getItems();
        return ItemProcessor.getInstance();
    }
}
