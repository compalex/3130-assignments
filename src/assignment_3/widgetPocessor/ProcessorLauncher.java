package assignment_3.widgetPocessor;

public class ProcessorLauncher {

    public static IWidgetProcessor launchAsyncProcessor() {
        return WidgetProcessor.getInstance();
    }
}
