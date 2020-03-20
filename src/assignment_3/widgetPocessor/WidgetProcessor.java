package assignment_3.widgetPocessor;

import java.util.Map;
import assignment_3.model.Invoice;

public class WidgetProcessor implements IWidgetProcessor {
    private static volatile WidgetProcessor instance;
    private WidgetService service;
    
    private WidgetProcessor() {
        service = WidgetService.getInstance();
    }
    
    public static WidgetProcessor getInstance() {
        if(instance == null) {
            instance = new WidgetProcessor();
        }
        return instance;
    }

    @Override
    public boolean processReceipt(int quantity, double price) {
        if(quantity <= 0 || price < 0) {
            return false;
        }
        return service.processReceipt(quantity, price);
    }

    @Override
    public Invoice processSales(int quantity) {
        if(quantity <= 0) {
            return null;
        }
        return service.processSales(quantity);
    }
    
    @Override
    public boolean processPromotion(double percentage, int num) {
        if(percentage < 0 || num <= 0) {
            return false;
        }
        return service.processPromotion(percentage, num);
    }

    @Override
    public Map<Double, Integer> getStockRemainder() {
        return service.getStockRemainder();
    }
}
