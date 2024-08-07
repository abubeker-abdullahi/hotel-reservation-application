package model;

import utils.ValidationUtils;

/**
 * Represents a customer with first name, last name, and email.
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Constructs a Customer object with the specified first name, last name, and email.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param email the email of the customer
     * @throws IllegalArgumentException if the first name, last name, or email is null or empty
     * @throws IllegalArgumentException if the email format is invalid
     */
    public Customer(String firstName, String lastName, String email) {
        super();
        
        ValidationUtils.customerValidationInputs(firstName, lastName, email); // validates non-null inputs
        ValidationUtils.emailValidation(email); // validates email format
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return the first name of the customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return the last name of the customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email of the customer.
     *
     * @return the email of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email of the customer.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a string representation of the Customer object
     */
    @Override
    public String toString() {
        return "Customer {firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "}";
    }
}
