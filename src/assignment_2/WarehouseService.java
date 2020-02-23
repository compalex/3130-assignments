package assignment_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WarehouseService {

    public static List<Warehouse> getWarehousesWithItems() {
        List<Warehouse> warehouses = getWarehouses();
        Set<Item> itemSet = getItems();
        
        for(Warehouse warehouse : warehouses) {
            Map<Item, Integer> itemMap = new HashMap<>();
            
            for(Item item : itemSet) {
                itemMap.put(item, 0);
            }
            warehouse.setItems(itemMap);
        }
        return warehouses;
    }
    
    public static List<Warehouse> getWarehouses() {
        return Factory.getInstance().getWarehouses();   
    }
    
    public static Set<Item> getItems() {
        return Factory.getInstance().getItems();
    }
    
    public static Warehouse getWarehouse(Constants.City city) {
        for(Warehouse warehouse : getWarehouses()) {
            if(city == warehouse.getCity()) {
                return warehouse;
            }
        }
        return null;
    }
    //addItem()
    //when add to warehouse, check if Map has this item//like lazy init

    public static void printWarehouse(Warehouse warehouse) {
        String line = warehouse.getCity() + " ";
        Map<Item, Integer> itemMap = warehouse.getItems();
        
        for(Item item : itemMap.keySet()) {
            line += item.getType() + "s:" + itemMap.get(item) + " ";
        }
        System.out.println(line);
    }
}
