package service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.Customer;

/**
 * CustomerService class provides methods to manage customers in the hotel reservation system.
 * It allows adding customers, retrieving a customer by email, and getting all customers.
 */
public class CustomerService {

    private static final Set<Customer> allCustomers = new HashSet<>();

    /**
     * Adds a new customer to the system.
     *
     * @param email     The email of the customer.
     * @param firstName The first name of the customer.
     * @param lastName  The last name of the customer.
     * @throws IllegalArgumentException If a customer with the given email already exists.
     */
    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        if (allCustomers.contains(customer)) {
            throw new IllegalArgumentException(email + " already exists.");
        }

        allCustomers.add(customer);
    }

    /**
     * Retrieves a customer by their email.
     *
     * @param customerEmail The email of the customer.
     * @return The Customer object if found, otherwise null.
     */
    public static Customer getCustomer(String customerEmail) {
        for (Customer customer : allCustomers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Retrieves all customers in the system.
     *
     * @return A collection of all Customer objects.
     */
    public static Collection<Customer> getAllCustomers() {
        return new HashSet<>(allCustomers);
    }
}
