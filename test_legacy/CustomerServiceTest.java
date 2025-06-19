import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService = new CustomerService();
    }

    @Test
    public void testGetCustomerEmail_existingCustomer() {
        String email = customerService.getCustomerEmail("C001");
        assertEquals("alice@example.com", email);
        email = customerService.getCustomerEmail("C002");
        assertEquals("bob@example.com", email);
    }

    @Test
    public void testGetCustomerEmail_nonExistingCustomer() {
        String email = customerService.getCustomerEmail("C999");
        assertEquals("Customer not found", email);
    }

    @Test
    public void testUpdateCustomerEmail_existingCustomer() {
        customerService.updateCustomerEmail("C001", "alice.new@example.com");
        String email = customerService.getCustomerEmail("C001");
        assertEquals("alice.new@example.com", email);
    }

    @Test
    public void testUpdateCustomerEmail_nonExistingCustomer() {
        // Should not throw exception
        customerService.updateCustomerEmail("C999", "ghost@example.com");
        String email = customerService.getCustomerEmail("C999");
        assertEquals("Customer not found", email);
    }
} 