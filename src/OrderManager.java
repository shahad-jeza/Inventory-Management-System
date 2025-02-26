import java.util.concurrent.*;

class OrderManager {
    private ExecutorService executor = Executors.newFixedThreadPool(4); // Increased thread pool size for high-volume orders

    public void processOrder(Order order) {
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
                }

                // logs >> sleep >> change state using setter (repeat same process)
                Thread.sleep(3000);

                synchronized (order) {
                    order.setOrderStatus(OrderStatus.DELIVERED);
                    System.out.println("Order " + order.getOrderId() + " status updated to " + order.getOrderStatus());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Catch any errors
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}