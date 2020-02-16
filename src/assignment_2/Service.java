package assignment_2;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static List<Warehouse> warehouses;
    
    public static List<Warehouse> getWarehouses() {
        if(warehouses == null || warehouses.isEmpty()) {
            warehouses = new ArrayList<>();
            
            warehouses.add(new Warehouse())
        }
    }
}
