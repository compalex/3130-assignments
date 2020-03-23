package assignment_3.widgetProcessor;

import java.util.List;
import assignment_3.model.Invoice;
import assignment_3.model.PromoRecord;
import assignment_3.model.ReceiptRecord;
import assignment_3.model.SalesRecord;
import assignment_3.model.WidgetPack;

public class WidgetProcessor implements IWidgetProcessor {
    private static volatile WidgetProcessor instance;
    private WidgetService service;
    
    private WidgetProcessor() {
        service = WidgetService.getInstance();
    }
    
    public synchronized static WidgetProcessor getInstance() {
        if(instance == null) {
            instance = new WidgetProcessor();
        }
        return instance;
    }

    @Override
    public synchronized boolean processReceipt(ReceiptRecord record) {
        if(record.getQuantity() <= 0 || record.getPrice() < 0) {
            return false;
        }
        return service.processReceipt(record);
    }

    @Override
    public synchronized Invoice processSales(SalesRecord record) {
        if(record.getQuantity() <= 0) {
            return null;
        }
        return service.processSales(record);
    }
    
    @Override
    public synchronized boolean processPromotion(PromoRecord record) {
        if(record.getDiscountPercentage() < 0) {
            return false;
        }
        return service.processPromotion(record);
    }

    @Override
    public synchronized List<WidgetPack> getStockRemainder() {
        return service.getStockRemainder();
    }
}
