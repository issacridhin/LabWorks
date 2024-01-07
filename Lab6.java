//First Part - Coin Change
import java.util.*;

public class CoinChange {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of Array: ");
        int n = input.nextInt();
        int[] coinsArr = new int[n];
        System.out.println("Enter the sum value: ");
        int sum = input.nextInt();
        Thread t1 = new Thread(() -> {

            synchronized (coinsArr) {

                System.out.print("Enter the coins value: ");
                for (int i = 0; i < n; i++) {
                    coinsArr[i] = input.nextInt();
                }

            }
        });
        List<List<Integer>> v = new ArrayList<>();
        Thread t2 = new Thread(() -> {
            synchronized (coinsArr) {
                combiFunction(coinsArr, sum, 0, new ArrayList<>(), v);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        for (List<Integer> combination : v) {
            System.out.println(combination);
        }

    }

    static void combiFunction(int[] array, int target, int start, List<Integer> current,
            List<List<Integer>> result) {
        if (target == 0) {

            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < array.length; i++) {

            if (array[i] <= target) {
                current.add(array[i]);
                combiFunction(array, target - array[i], i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

}











//Second Part - Enhanced Order fulfillment System
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InsufficientInventoryException extends Exception {
    public InsufficientInventoryException(String message) {
        super(message);
    }
}

class OrderCancellationException extends Exception {
    public OrderCancellationException(String message) {
        super(message);
    }
}

class Item {
    private String itemName;
    private int quantity;

    public Item(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Order {
    private int orderId;
    private List<Item> items;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}

class EnhancedOrderFulfillmentSystem {
    private int inventory;
    private List<Order> orders;

    public EnhancedOrderFulfillmentSystem(int initialInventory) {
        this.inventory = initialInventory;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        try {
            updateInventory(order);
            orders.add(order);
            System.out.println("Order " + order.getOrderId() + " placed successfully.");
        } catch (InsufficientInventoryException e) {
            System.out.println("Order " + order.getOrderId() + " failed: " + e.getMessage());
        }
    }

    public void startProcessing() {
        // Dummy implementation of order processing
        System.out.println("Order processing started.");
    }

    public void waitForCompletion() {
        // Dummy implementation to wait for order completion
        System.out.println("Waiting for order completion.");
    }

    public void updateInventory(Order order) throws InsufficientInventoryException {
        for (Item item : order.getItems()) {
            if (!checkInventoryAvailability(item)) {
                throw new InsufficientInventoryException("Insufficient stock for item: " + item.getItemName());
            }
            inventory -= item.getQuantity();
        }
    }

    public boolean checkInventoryAvailability(Item item) {
        return inventory >= item.getQuantity();
    }

    public void trackOrderStatus(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                System.out.println("Order " + orderId + " Status:");
                System.out.println("Items:");

                for (Item item : order.getItems()) {
                    System.out.println("- " + item.getItemName() + ": " + item.getQuantity() + " units");
                }

                System.out.println("Current Inventory Level: " + inventory);

                // Additional information or processing related to order status can be added here

                return;
            }
        }
        System.out.println("Order " + orderId + " not found.");
    }

    public void displayInventory() {
        System.out.println("Current Inventory Level: " + inventory);
    }

    public void shutdown() {
        // No need to shutdown in this simplified version
    }
}

public class EnhancedOrderFulfillmentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EnhancedOrderFulfillmentSystem orderFulfillmentSystem = new EnhancedOrderFulfillmentSystem(100);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Place Order");
            System.out.println("2. Start Processing");
            System.out.println("3. Wait for Completion");
            System.out.println("4. Track Order Status");
            System.out.println("5. Update Inventory");
            System.out.println("6. Check Inventory Availability");
            System.out.println("7. Display Current Inventory");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Place Order
                    System.out.println("Enter order ID:");
                    int orderId = scanner.nextInt();
                    Order order = new Order(orderId);
                    // Get order information from the user
                    while (true) {
                        System.out.println("Enter item name (or 'done' to finish):");
                        String itemName = scanner.next();

                        if (itemName.equalsIgnoreCase("done")) {
                            break;
                        }

                        System.out.println("Enter quantity:");
                        int quantity = scanner.nextInt();

                        Item item = new Item(itemName, quantity);
                        order.addItem(item);
                    }
                    orderFulfillmentSystem.placeOrder(order);
                    break;
                case 2:
                    // Start Processing
                    orderFulfillmentSystem.startProcessing();
                    break;
                case 3:
                    // Wait for Completion
                    orderFulfillmentSystem.waitForCompletion();
                    break;
                case 4:
                    // Track Order Status
                    System.out.println("Enter order ID to track status:");
                    int orderIdToTrack = scanner.nextInt();
                    orderFulfillmentSystem.trackOrderStatus(orderIdToTrack);
                    break;
                case 5:
                    // Update Inventory
                    System.out.println("Update Inventory - Enter order ID:");
                    int orderIdToUpdate = scanner.nextInt();
                    Order orderToUpdate = new Order(orderIdToUpdate);
                    // Get order information from the user
                    while (true) {
                        System.out.println("Enter item name to update inventory (or 'done' to finish):");
                        String itemName = scanner.next();

                        if (itemName.equalsIgnoreCase("done")) {
                            break;
                        }

                        System.out.println("Enter quantity:");
                        int quantity = scanner.nextInt();

                        Item item = new Item(itemName, quantity);
                        orderToUpdate.addItem(item);
                    }
                    try {
                        orderFulfillmentSystem.updateInventory(orderToUpdate);
                        System.out.println("Inventory updated for order " + orderIdToUpdate);
                    } catch (InsufficientInventoryException e) {
                        System.out.println("Failed to update inventory: " + e.getMessage());
                    }
                    break;
                case 6:
                    // Check Inventory Availability
                    System.out.println("Check Inventory Availability - Enter item name:");
                    String itemNameToCheck = scanner.next();
                    System.out.println("Enter quantity:");
                    int quantityToCheck = scanner.nextInt();
                    Item itemToCheck = new Item(itemNameToCheck, quantityToCheck);
                    boolean availability = orderFulfillmentSystem.checkInventoryAvailability(itemToCheck);
                    if (availability) {
                        System.out.println("Item is available in the inventory.");
                    } else {
                        System.out.println("Item is NOT available in the inventory.");
                    }
                    break;
                case 7:
                    // Display Current Inventory
                    orderFulfillmentSystem.displayInventory();
                    break;
                case 8:
                    // Exit
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 8);

        orderFulfillmentSystem.shutdown();
        scanner.close();
    }
}
