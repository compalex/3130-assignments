package assignment_2;

import java.util.ArrayList;
import java.util.List;

public class WarehouseService {
    private static WarehouseService instance;
    private List<Warehouse> warehouses;
    
    public static WarehouseService getInstance() {
        if(instance == null) {
            instance = new WarehouseService();
        }
        return instance;
    }

    public List<Warehouse> getWarehouses(List<Double> prices) {
        if(warehouses == null) {
            warehouses = new ArrayList<>();
        }
        if(warehouses.isEmpty()) {
            for(Constants.City city : Constants.City.values()) {
                warehouses.add(new Warehouse(city, prices)); 
            }
        }
        return warehouses;   
    }
    
    public Warehouse getWarehouse(Constants.City city) {
        for(Warehouse warehouse : warehouses) {
            if(city == warehouse.getCity()) {
                return warehouse;
            }
        }
        return null;
    }
    //addItem()
    //when add to warehouse, check if Map has this item//like lazy init
}
