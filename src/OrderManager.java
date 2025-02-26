import java.util.concurrent.*;
import java.io.*;
import java.util.*;

class OrderManager {
    private ExecutorService executor = Executors.newFixedThreadPool(4);
    private static final String ORDERS_FILE = "orders.txt";
    private List<Order> orders = new ArrayList<>();

    public OrderManager() {
        loadOrdersFromFile();
    }

    public void processOrder(Order order) {
        // Add the order to our in-memory list
        orders.add(order);

        // Save to file immediately
        saveOrdersToFile();

        executor.submit(() -> {
            try {
                System.out.println("Processing order - updated by merging the two branches...");

                // logs >> sleep >> change state using setter
                System.out.println("Processing order: " + order.getOrderId());
                Thread.sleep(2000);

                // Synchronizing the order status update
                synchronized (order) {
                    order.setOrderStatus(OrderStatus.SHIPPED);
                    System.out.println("Order " + order.getOrderId() + " status updated to " + order.getOrderStatus());
                    // Save updated status
                    saveOrdersToFile();
                }

                // logs >> sleep >> change state using setter (repeat same process)
                Thread.sleep(3000);

                synchronized (order) {
                    order.setOrderStatus(OrderStatus.DELIVERED);
                    System.out.println("Order " + order.getOrderId() + " status updated to " + order.getOrderStatus());
                    // Save updated status
                    saveOrdersToFile();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Catch any errors
            }
        });
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    private void saveOrdersToFile() {
        try (FileWriter fileWriter = new FileWriter(ORDERS_FILE);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (Order order : orders) {
                // Format: orderId,customerName,productId,quantity,orderStatus
                String orderLine = String.format("%s,%s,%s,%d,%s",
                        order.getOrderId().toString(),
                        order.getCustomerName(),
                        order.getProductId().toString(),
                        order.getQuantity(),
                        order.getOrderStatus().toString());

                bufferedWriter.write(orderLine);
                bufferedWriter.newLine();
            }

            System.out.println("Orders saved to file successfully");
        } catch (IOException e) {
            System.err.println("Error saving orders to file: " + e.getMessage());
        }
    }

    private void loadOrdersFromFile() {
        File file = new File(ORDERS_FILE);

        // If file doesn't exist yet, just return
        if (!file.exists()) {
            System.out.println("Orders file not found. Starting with empty orders list.");
            return;
        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        UUID orderId = UUID.fromString(parts[0]);
                        String customerName = parts[1];
                        UUID productId = UUID.fromString(parts[2]);
                        int quantity = Integer.parseInt(parts[3]);
                        OrderStatus status = OrderStatus.valueOf(parts[4]);

                        // Create and restore the order
                        Order order = new Order(customerName, productId, quantity);
                        // Use reflection to set the orderId since it's generated in constructor
                        java.lang.reflect.Field field = Order.class.getDeclaredField("orderId");
                        field.setAccessible(true);
                        field.set(order, orderId);

                        order.setOrderStatus(status);
                        orders.add(order);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing order line: " + line + " - " + e.getMessage());
                }
            }

            System.out.println("Loaded " + orders.size() + " orders from file");
        } catch (IOException e) {
            System.err.println("Error loading orders from file: " + e.getMessage());
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}