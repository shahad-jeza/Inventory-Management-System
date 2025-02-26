import java.util.concurrent.*;

class OrderManager {
    private ExecutorService executor = Executors.newFixedThreadPool(2); // two threads for example

    public void processOrder(Order order) {
        executor.submit(() -> {
            try {

                System.out.println("Processing order - updated by merging the two branches...");

                // logs >> sleep >> change state using setter
                System.out.println("Processing order: " + order.getOrderId());
                Thread.sleep(2000);
                order.setOrderStatus(OrderStatus.SHIPPED);
                // logs >> sleep >> change state using setter (repeat same proccess)
                System.out.println("Order " + order.getOrderId() + " status updated to "+ order.getOrderStatus());
                Thread.sleep(3000);
                order.setOrderStatus(OrderStatus.DELIVERED);
                System.out.println("Order " + order.getOrderId() + " status updated to "+  order.getOrderStatus());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // catch any errors
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
