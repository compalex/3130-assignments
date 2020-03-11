package assignment_2.itemProcessor;

import java.util.Set;
import assignment_2.CSVReader;
import assignment_2.model.AbstractItem;

public class ProcessorLauncher {

    public static ItemProcessor start() {
        //Set<AbstractItem> items = CSVReader.getItems();
        return ItemProcessor.getInstance();
    }
}
