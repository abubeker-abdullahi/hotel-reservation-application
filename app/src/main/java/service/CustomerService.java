package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Customer;

public class CustomerService {
    private static final Map<String, Customer> customersList = new HashMap<>();

    public static void addCustomer(String email, String firstName, String lastName) {
        if (customersList.containsKey(email)) {
            throw new IllegalArgumentException(email + " already exists.");
        }

        if (email == null || firstName == null || lastName == null) {
            throw new IllegalArgumentException("Email, first name, and last name must not be null.");
        }

        Customer customer = new Customer(firstName, lastName, email);
        customersList.put(email, customer);
    }

    public static Customer getCustomer(String customerEmail) {
        return customersList.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers() {
        return customersList.values();
    }
}
