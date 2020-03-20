package assignment_3.widgetPocessor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import assignment_3.model.Widget;

public class WidgetManager {
    private static WidgetManager instance;
    private LinkedList<Widget> widgets;
    private LinkedList<Double> promotions;
    
    private WidgetManager() {
        widgets = new LinkedList<>();
        promotions = new LinkedList<>();
    }
    
    public static WidgetManager getInstance() {
        if(instance == null) {
            instance = new WidgetManager();
        }
        return instance;
    }
    
    public List<Widget> pollWidgets(int amount) {
        List<Widget> widgetList = new ArrayList<>();
        if(amount > widgets.size()) {
            amount = widgets.size();
        }
        
        for(int i = 0; i < amount; i++) {
            widgetList.add(widgets.poll());
        }
        return widgetList;
    }
    
    public List<Widget> getWidgets() {
        return widgets;
    }
    
    public boolean addWidgets(List<Widget> widgetList) {
        if(widgetList == null || widgetList.isEmpty()) {
            return false;
        }
        return widgets.addAll(widgetList);
    }
    
    public boolean addPromotions(List<Double> promotionList) {
        if(promotionList == null || promotionList.isEmpty()) {
            return false;
        }
        return promotions.addAll(promotionList);
    }
    
    public Double pollPromotion() {
        if(promotions.isEmpty()) {
            return 0.0;
        }
        return promotions.poll();
    }
}
