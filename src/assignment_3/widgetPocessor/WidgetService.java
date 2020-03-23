package assignment_3.widgetProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import assignment_3.Constants;
import assignment_3.model.Invoice;
import assignment_3.model.PromoRecord;
import assignment_3.model.ReceiptRecord;
import assignment_3.model.SalesRecord;
import assignment_3.model.Widget;
import assignment_3.model.WidgetPack;

public class WidgetService {
    private static WidgetService instance;
    private WidgetManager manager;
    
    private WidgetService() {
        manager = WidgetManager.getInstance();
    }
    
    public static WidgetService getInstance() {
        if(instance == null) {
            instance = new WidgetService();
        }
        return instance;
    }

    public boolean processReceipt(ReceiptRecord record) {
        List<Widget> widgetList = new ArrayList<>();
        
        for(int i = 0; i < record.getQuantity(); i++) {
            Widget widget = new Widget(record.getPrice());
            widgetList.add(widget);
        }
        return manager.addWidgets(widgetList);
    }

    public Invoice processSales(SalesRecord record) {
        List<Widget> widgets = manager.pollWidgets(record.getQuantity());
        List<WidgetPack> widgetPacks = getWidgetPacks(widgets);
        double promoDiscount = manager.pollPromotion();
        double totalPrice = getTotalPrice(widgetPacks, promoDiscount);
        return new Invoice()
                .setWidgetPacks(widgetPacks)
                .setPromoDiscount(promoDiscount)
                .setTotalPrice(totalPrice);
    }

    private List<WidgetPack> getWidgetPacks(List<Widget> widgets) {
        List<WidgetPack> widgetPacks = new ArrayList<>();
        Map<Double, Integer> amountByPrice = new HashMap<>();
        
        for(Widget widget : widgets) {
            amountByPrice.merge(widget.getPrice(), 1, Integer::sum);
        }
        amountByPrice = adjustPrices(amountByPrice);
        amountByPrice.forEach((price, amount) -> widgetPacks.add(new WidgetPack(price, amount)));
        return widgetPacks;
    }
    
    private double getTotalPrice(List<WidgetPack> widgetPacks, double promoDiscount) {
        double totalPrice = 0;
        
        for(WidgetPack pack : widgetPacks) {
            totalPrice += pack.getSales();
        }
        totalPrice *= (double)(100 - promoDiscount) / 100;
        return totalPrice;
    }
    
    public boolean processPromotion(PromoRecord record) {
        List<Double> promotionList = new ArrayList<>();
        
        for(int i = 0; i < Constants.DISCOUNTED_CUSTOMERS_NUM; i++) {
            promotionList.add(record.getDiscountPercentage());
        }
        return manager.addPromotions(promotionList);
    }
    
    public List<WidgetPack> getStockRemainder() {
        List<Widget> widgets = manager.getWidgets();
        return getWidgetPacks(widgets);
    }
    
    private Map<Double, Integer> adjustPrices(Map<Double, Integer> prevMap) {
        Map<Double, Integer> adjustedMap = new HashMap<>();
      
        for(Double price : prevMap.keySet()) {
            double adjustedPrice = price * (100 + Constants.STORE_MARKUP_PERCENTAGE) / 100;
            adjustedMap.put(adjustedPrice, prevMap.get(price));
        }
        return adjustedMap;
    }
}
