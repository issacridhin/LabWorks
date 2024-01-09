import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + "]";
    }
}

class Product {
    private String id;
    private String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }
}

class AmazonCRMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of customers:");
        int numCustomers = scanner.nextInt();

        System.out.println("Enter the number of products:");
        int numProducts = scanner.nextInt();

        System.out.println("Enter customer IDs and names:");
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < numCustomers; i++) {
            String id = scanner.next();
            String name = scanner.next();
            customers.add(new Customer(id, name));
        }

        System.out.println("Enter product IDs and names:");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numProducts; i++) {
            String id = scanner.next();
            String name = scanner.next();
            products.add(new Product(id, name));
        }

        List<Customer> sortedCustomers = customers.stream()
                                                 .sorted(Comparator.comparing(Customer::getId))
                                                 .collect(Collectors.toList());

        List<Product> sortedProducts = products.stream()
                                              .sorted(Comparator.comparing(Product::getId))
                                              .collect(Collectors.toList());

        System.out.println("Sorted Customers: " + sortedCustomers);
        System.out.println("Sorted Products: " + sortedProducts);
    }
}
