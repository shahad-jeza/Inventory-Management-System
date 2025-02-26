import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // create the  two products
        Product laptop = new Product("Laptop", 1200.0, 20, 5);
        Product smartphone = new Product("Smartphone", 800.0, 10, 3);

        // take user input
        System.out.print("Enter average daily sales for Laptop: ");
        double laptopSales = scanner.nextDouble();
        System.out.print("Enter average daily sales for Smartphone: ");
        double phoneSales = scanner.nextDouble();

        // print details as in the expected output
        System.out.println("\nProduct ID: " + laptop.getProductId());
        System.out.println("Name: " + laptop.getName());
        System.out.println("Price: $" + laptop.getPrice());
        System.out.println("Stock Level: " + laptop.getStockLevel());
        System.out.println("Reorder Threshold: " + laptop.getReorderThreshold());
        System.out.println("----------------------------");

        System.out.println("Product ID: " + smartphone.getProductId());
        System.out.println("Name: " + smartphone.getName());
        System.out.println("Price: $" + smartphone.getPrice());
        System.out.println("Stock Level: " + smartphone.getStockLevel());
        System.out.println("Reorder Threshold: " + smartphone.getReorderThreshold());
        System.out.println("----------------------------");

        // use the prediction function for both of the products
        System.out.println("Stock Prediction for Laptop:");
        System.out.println("Days until stock out: " + StockPredictor.predictStockDepletion(laptop, laptopSales));
        System.out.println("Restock Suggestion: " + StockPredictor.suggestRestocking(laptop, laptopSales));

        System.out.println("Stock Prediction for Smartphone:");
        System.out.println("Days until stock out: " + StockPredictor.predictStockDepletion(smartphone, phoneSales));
        System.out.println("Restock Suggestion: " + StockPredictor.suggestRestocking(smartphone, phoneSales));

        OrderManager orderManager = new OrderManager();
        Order order1 = new Order("shahad", laptop.getProductId(), 1);
        Order order2 = new Order("raghad", smartphone.getProductId(), 2);

        orderManager.processOrder(order1);
        orderManager.processOrder(order2);

        // clean up for optmization
        scanner.close();
        orderManager.shutdown();
    }
}
