package assignment_3.demoClient;

import java.util.List;
import java.util.Map;
import assignment_3.Constants;
import assignment_3.model.Invoice;
import assignment_3.model.PromoRecord;
import assignment_3.model.ReceiptRecord;
import assignment_3.model.Record;
import assignment_3.model.SalesRecord;
import assignment_3.model.WidgetPack;
import assignment_3.widgetProcessor.IWidgetProcessor;
import assignment_3.widgetProcessor.WidgetProcessor;

public class RecordService {
    private IWidgetProcessor processor;
    
    public RecordService() {
        processor = WidgetProcessor.getInstance();
    }
    
    public List<Record> getRecords() {
        List<Record> records = CSVReader.getRecords();
        if(records == null || records.isEmpty()) {
            System.err.println(Constants.ERROR_DATA_MSG);
        }
        return records;
    }

    public void processRecord(Record record) { 
        if(record == null) {
            System.err.println(Constants.ERROR_DATA_MSG);
            return;
        }
        switch(record.getType()) {
            case RECEIPT:
                processReceiptRecord((ReceiptRecord)record);
                break;
            case SALES:
                processSalesRecord((SalesRecord)record);
                break;
            case PROMOTION:
                processPromotion((PromoRecord)record);
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);
        }
        System.out.println();
    }

    private void processReceiptRecord(ReceiptRecord record) {
        boolean isSuccess = processor.processReceipt(record);
        if(isSuccess) {
            System.out.printf(Constants.PRINT_RECEIPT, record.getQuantity(), record.getPrice());
        } else {
            System.err.println(Constants.ERROR_DATA_MSG);
        }
    }

    private void processSalesRecord(SalesRecord record) {
        Invoice invoice = processor.processSales(record);
        if(invoice != null) {
            System.out.print(invoice.toString());
            int unsold = record.getQuantity() - invoice.getTotalAmount();
            if(unsold > 0) {
                System.out.printf(Constants.PRINT_SALES_REMAINDER, unsold);
            }
        } else {
            System.err.println(Constants.ERROR_DATA_MSG);
        }   
    }
    
    private void processPromotion(PromoRecord record) {
        boolean isSuccess = processor.processPromotion(record);
        if(isSuccess) {
            System.out.printf(Constants.PRINT_PROMOTION, Constants.DISCOUNTED_CUSTOMERS_NUM, record.getDiscountPercentage());
        } else {
            System.err.println(Constants.ERROR_DATA_MSG);
        }
    }
    
    public void printStockRemainder() {
        List<WidgetPack> widgetPacks = processor.getStockRemainder();
        if(!widgetPacks.isEmpty()) {
            int totalNum = 0;
            
            for(WidgetPack pack : widgetPacks) {
                totalNum += pack.getQuantity();
            }
            System.out.printf(Constants.PRINT_REMAIN_HEADER, totalNum);
            
            for(WidgetPack pack : widgetPacks) {
                System.out.printf(Constants.PRINT_REMAIN_LINE, pack.getQuantity(), pack.getPrice());
            }
        } else {
            System.out.println(Constants.PRINT_REMAIN_EMPTY);
        }
    }
}
