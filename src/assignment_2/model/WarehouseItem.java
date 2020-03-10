package assignment_2.model;

import assignment_2.Constants;
import assignment_2.Constants.City;

public class WarehouseItem extends AbstractItem {
    private City city;
    
    public WarehouseItem(Product product, double price, City city) {
        super(product, price);
        this.city = city;
    }

    public Constants.City getCity() {
        return city;
    }
}
