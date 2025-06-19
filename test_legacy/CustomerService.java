import java.util.Hashtable;

public class CustomerService {

    private Hashtable customerTable = new Hashtable(); // raw type, no generics

    public CustomerService() {
        // Adding mock data
        Customer c1 = new Customer("C001", "Alice", "alice@example.com");
        Customer c2 = new Customer("C002", "Bob", "bob@example.com");
        customerTable.put(c1.getId(), c1);
        customerTable.put(c2.getId(), c2);
    }

    public String getCustomerEmail(String customerId) {
        Customer customer = (Customer) customerTable.get(customerId); // cast needed
        if (customer != null) {
            return customer.getEmail();
        } else {
            return "Customer not found";
        }
    }

    public void updateCustomerEmail(String customerId, String newEmail) {
        Customer customer = (Customer) customerTable.get(customerId);
        if (customer != null) {
            customer.setEmail(newEmail);
        }
    }
}

class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
