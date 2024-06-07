package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for the {@link model.Customer} class.
 */
public class CustomerTest {

    private final Customer customer = new Customer("Abubeker", "Abdullahi", "abubeker@email.com");

    /**
     * Tests the setter and getter methods of the {@link model.Customer} class.
     */
    @Test
    public void testCustomerSetterAndGetter() {
        customer.setFirstName("Abubeker");
        customer.setLastName("Abdullahi1");
        customer.setEmail("abubeker1@email.com");

        assertEquals("Abubeker", customer.getFirstName());
        assertEquals("Abdullahi1", customer.getLastName());
        assertEquals("abubeker1@email.com", customer.getEmail());
    }

    /**
     * Tests that an empty first name throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testEmptyFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("", "Abdullahi", "abubeker@email.com");
        });
    }

    /**
     * Tests that an empty last name throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testEmptyLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Abubeker", "", "abubeker@email.com");
        });
    }

    /**
     * Tests that an empty email throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testEmptyEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Abubeker", "Abdullahi", "");
        });
    }

    /**
     * Tests that a null first name throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null, "Abdullahi", "abubeker@email.com");
        });
    }

    /**
     * Tests that a null last name throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Abubeker", null, "abubeker@email.com");
        });
    }

    /**
     * Tests that a null email throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Abubeker", "Abdullahi", null);
        });
    }

    /**
     * Tests the {@link model.Customer#toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("Customer {firstName=Abubeker, lastName=Abdullahi, email=abubeker@email.com}", customer.toString());
    }

    /**
     * Tests that an invalid email format throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testEmailValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Abubeker", "Abdullahi", "@email.com");
        });
    }
}
