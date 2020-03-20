package assignment_3.model;

import java.util.Map;
import assignment_3.Constants;

public class Invoice {    
    private Map<Double, Integer> amountByPriceMap;
    private double promoDiscount;
    private int unsold;
    
    public void setAmountByPriceMap(Map<Double, Integer> map) {
        amountByPriceMap = map;
    }

    public void setPromoDiscount(double promoDiscount) {
        this.promoDiscount = promoDiscount;
    }
    
    public void setUnsold(int unsold) {
        this.unsold = unsold;
    }
    
    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append(String.format(Constants.PRINT_SALES_HEADER, getTotalAmount()));       
        double totalPrice = 0;
        
        for(Double price : amountByPriceMap.keySet()) {
            double salesPrice = price * amountByPriceMap.get(price);
            int amount = amountByPriceMap.get(price);
            toPrint.append(String.format(Constants.PRINT_SALES_RECORD, amount, price, salesPrice));
            totalPrice += salesPrice;
        }
        if(promoDiscount > 0) {
            toPrint.append(String.format(Constants.PRINT_SALES_PROMOTION, promoDiscount));
            totalPrice -= totalPrice * promoDiscount / 100;
        }
        toPrint.append(String.format(Constants.PRINT_SALES_ENDING, totalPrice));
        if(unsold > 0) {
            toPrint.append(String.format(Constants.PRINT_SALES_REMAINDER, unsold));
        }
        return toPrint.toString();
    }

    public int getTotalAmount() {
        int total = 0;
        
        for(Double price : amountByPriceMap.keySet()) {
            total += amountByPriceMap.get(price);
        }
        return total;
    }
}
