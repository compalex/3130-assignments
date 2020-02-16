package assignment_2;

import java.util.ArrayList;
import java.util.List;

public class WarehouseFactory {
    public static List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        
        for(Constants.City city : Constants.City.values()) {
           warehouses.add(new Warehouse(city)); 
        }
        return warehouses;
    }
}
