public class StockPredictor {

    public static int predictStockDepletion(Product product, double averageDailySales) {
        if (averageDailySales <= 0) {
            throw new IllegalArgumentException("Average daily sales must be greater than zero.");
        }
        return (int) Math.ceil(product.getStockLevel() / averageDailySales);
    }

    public static String suggestRestocking(Product product, double averageDailySales) {
        int daysUntilDepletion = predictStockDepletion(product, averageDailySales);
        if (daysUntilDepletion <= 0) {
            return "Stock is already depleted. Immediate restocking required.";
        } else if (product.getStockLevel() <= product.getReorderThreshold()) {
            return "Stock is low. Consider restocking soon.";
        } else {
            return "Moderate: Consider restocking soon.";
        }
    }

}
