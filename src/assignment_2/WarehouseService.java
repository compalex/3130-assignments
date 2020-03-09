package assignment_2;

import java.util.Map;
import assignment_2.Constants.City;
import model.Item;

public class WarehouseService {
    
    public static void printWarehouse(City city, WarehouseManager manager) {
        Map<Item.Type, Integer> itemMap = manager.getWarehouse(city).getItems();
        String line = "Result: Warehouse at " + city + " has";
        
        line += " " + Item.Type.Laptop + ":" + itemMap.get(Item.Type.Laptop);
        line += " " + Item.Type.Printer + ":" + itemMap.get(Item.Type.Printer);
        line += " " + Item.Type.Table + ":" + itemMap.get(Item.Type.Table);
        
        System.out.println(line);
    }
}
