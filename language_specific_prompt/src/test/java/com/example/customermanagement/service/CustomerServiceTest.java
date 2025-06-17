package com.example.customermanagement.service;

import com.example.customermanagement.exception.CustomerException;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setEmail("john.doe@example.com");
        testCustomer.setPhoneNumber("+1234567890");
        testCustomer.setAddress("123 Main St");
        testCustomer.setActive(true);
    }

    @Test
    void createCustomer_Success() {
        when(customerRepository.existsByEmail(anyString())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer createdCustomer = customerService.createCustomer(testCustomer);

        assertNotNull(createdCustomer);
        assertEquals(testCustomer.getEmail(), createdCustomer.getEmail());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void createCustomer_DuplicateEmail() {
        when(customerRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(CustomerException.class, () -> customerService.createCustomer(testCustomer));
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void getCustomerById_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);

        assertTrue(foundCustomer.isPresent());
        assertEquals(testCustomer.getId(), foundCustomer.get().getId());
    }

    @Test
    void getCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);

        assertTrue(foundCustomer.isEmpty());
    }

    @Test
    void getAllCustomers_Success() {
        List<Customer> customers = Arrays.asList(testCustomer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> foundCustomers = customerService.getAllCustomers();

        assertNotNull(foundCustomers);
        assertEquals(1, foundCustomers.size());
        assertEquals(testCustomer.getId(), foundCustomers.get(0).getId());
    }

    @Test
    void updateCustomer_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(customerRepository.existsByEmail(anyString())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer updatedCustomer = customerService.updateCustomer(1L, testCustomer);

        assertNotNull(updatedCustomer);
        assertEquals(testCustomer.getId(), updatedCustomer.getId());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void updateCustomer_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerException.class, () -> customerService.updateCustomer(1L, testCustomer));
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_Success() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        assertDoesNotThrow(() -> customerService.deleteCustomer(1L));
        verify(customerRepository).deleteById(1L);
    }

    @Test
    void deleteCustomer_NotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        assertThrows(CustomerException.class, () -> customerService.deleteCustomer(1L));
        verify(customerRepository, never()).deleteById(anyLong());
    }
} 