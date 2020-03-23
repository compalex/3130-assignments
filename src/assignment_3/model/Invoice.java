package assignment_3.model;

import java.util.List;
import assignment_3.Constants;

public class Invoice {    
    private List<WidgetPack> widgetPacks;
    private double promoDiscount;
    private double totalPrice;
    
    public Invoice setWidgetPacks(List<WidgetPack> widgetPacks) {
        this.widgetPacks = widgetPacks;
        return this;
    }

    public Invoice setPromoDiscount(double promoDiscount) {
        this.promoDiscount = promoDiscount;
        return this;
    }
    
    public Invoice setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append(String.format(Constants.PRINT_SALES_HEADER, getTotalAmount()));       
        
        for(WidgetPack pack : widgetPacks) {
            toPrint.append(String.format(Constants.PRINT_SALES_RECORD, 
                    pack.getQuantity(), pack.getPrice(), pack.getSales()));
        }
        if(promoDiscount > 0) {
            toPrint.append(String.format(Constants.PRINT_SALES_PROMOTION, promoDiscount));
        }
        toPrint.append(String.format(Constants.PRINT_SALES_ENDING, totalPrice));
        return toPrint.toString();
    }

    public int getTotalAmount() {
        int total = 0;
        
        for(WidgetPack pack : widgetPacks) {
            total += pack.getQuantity();
        }
        return total;
    }
}
