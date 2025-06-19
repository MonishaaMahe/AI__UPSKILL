package com.example.customeronboarding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerServiceTest {
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
        // Use reflection to set the private field for testing
        try {
            java.lang.reflect.Field repoField = CustomerService.class.getDeclaredField("customerRepository");
            repoField.setAccessible(true);
            repoField.set(customerService, customerRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDuplicateEmailCaseInsensitive() {
        Customer c1 = createCustomer("John", "Test@Email.com", 25, "Addr");
        customerService.onboardCustomer(c1);
        Customer c2 = createCustomer("Jane", "test@email.com", 30, "Addr2");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> customerService.onboardCustomer(c2));
        assertTrue(ex.getMessage().contains("Email already exists"));
    }

    @Test
    void testAgeAtBoundaries() {
        Customer c1 = createCustomer("A", "a@a.com", 18, "Addr");
        assertDoesNotThrow(() -> customerService.onboardCustomer(c1));
        Customer c2 = createCustomer("B", "b@b.com", 60, "Addr");
        assertDoesNotThrow(() -> customerService.onboardCustomer(c2));
    }

    @Test
    void testAgeBelowMinimum() {
        Customer c = createCustomer("A", "a2@a.com", 17, "Addr");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> customerService.onboardCustomer(c));
        assertTrue(ex.getMessage().contains("age must be between 18 and 60"));
    }

    @Test
    void testAgeAboveMaximum() {
        Customer c = createCustomer("A", "a3@a.com", 61, "Addr");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> customerService.onboardCustomer(c));
        assertTrue(ex.getMessage().contains("age must be between 18 and 60"));
    }

    @Test
    void testBlankFields() {
        Customer c = createCustomer("", "", 25, "");
        assertThrows(Exception.class, () -> customerService.onboardCustomer(c));
    }

    @Test
    void testWhitespaceFields() {
        Customer c = createCustomer("   ", "   ", 25, "   ");
        assertThrows(Exception.class, () -> customerService.onboardCustomer(c));
    }
    
    @Test
    void testSpecialCharacters() {
        Customer c = createCustomer("!@#$$%^&*()", "special@mail.com", 25, "<>?{}|~");
        assertDoesNotThrow(() -> customerService.onboardCustomer(c));
    }

    @Test
    void testNegativeAndZeroAge() {
        Customer c1 = createCustomer("A", "neg@a.com", -1, "Addr");
        assertThrows(Exception.class, () -> customerService.onboardCustomer(c1));
        Customer c2 = createCustomer("B", "zero@b.com", 0, "Addr");
        assertThrows(Exception.class, () -> customerService.onboardCustomer(c2));
    }

    @Test
    void testConcurrentDuplicateEmail() throws InterruptedException {
        int threads = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);
        String email = "concurrent@mail.com";
        Runnable task = () -> {
            try {
                customerService.onboardCustomer(createCustomer("User", email, 25, "Addr"));
            } catch (Exception ignored) {}
            latch.countDown();
        };
        for (int i = 0; i < threads; i++) executor.submit(task);
        latch.await();
        assertEquals(2, customerRepository.findAll().stream().filter(c -> c.getEmail().equals(email)).count());
    }

    private Customer createCustomer(String name, String email, int age, String address) {
        Customer c = new Customer();
        c.setName(name);
        c.setEmail(email);
        c.setAge(age);
        c.setAddress(address);
        return c;
    }
} 