package assignment_3.widgetPocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import assignment_3.Constants;
import assignment_3.model.Invoice;
import assignment_3.model.Widget;

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

    public boolean processReceipt(int quantity, double price) {
        List<Widget> widgetList = new ArrayList<>();
        
        for(int i = 0; i < quantity; i++) {
            Widget widget = new Widget(price);
            widgetList.add(widget);
        }
        return manager.addWidgets(widgetList);
    }

    public Invoice processSales(int quantity) {
        Invoice invoice = new Invoice();
        List<Widget> widgets = manager.pollWidgets(quantity);
        Map<Double, Integer> amountByPriceMap = getWidgetMap(widgets);
        amountByPriceMap = adjustPrices(amountByPriceMap);
        invoice.setAmountByPriceMap(amountByPriceMap);
        invoice.setUnsold(quantity - widgets.size());
        double promoDiscount = manager.pollPromotion();
        if(promoDiscount > 0) {
            invoice.setPromoDiscount(promoDiscount);
        }
        return invoice;
    }
    
    private Map<Double, Integer> adjustPrices(Map<Double, Integer> prevMap) {
        Map<Double, Integer> adjustedMap = new HashMap<>();
      
        for(Double price : prevMap.keySet()) {
            double adjustedPrice = price * (100 + Constants.STORE_MARKUP_PERCENTAGE) / 100;
            adjustedMap.put(adjustedPrice, prevMap.get(price));
        }
        return adjustedMap;
    }

    public boolean processPromotion(double percentage, int num) {
        List<Double> promotionList = new ArrayList<>();
        
        for(int i = 0; i < num; i++) {
            promotionList.add(percentage);
        }
        return manager.addPromotions(promotionList);
    }
    
    public Map<Double, Integer> getStockRemainder() {
        List<Widget> widgets = manager.getWidgets();
        return getWidgetMap(widgets);
    }

    private Map<Double, Integer> getWidgetMap(List<Widget> widgets) {
        Map<Double, Integer> map = new HashMap<>();
        
        for(Widget widget : widgets) {
            map.merge(widget.getPrice(), 1, Integer::sum);
        }
        return map;
    }
}
