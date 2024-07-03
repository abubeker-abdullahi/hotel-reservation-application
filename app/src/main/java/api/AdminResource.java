package api;

import java.util.Collection;
import java.util.List;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

/**
 * The AdminResource class provides an API for administrative tasks related to the hotel.
 * This includes functionalities for managing customers, rooms, and reservations.
 */
public class AdminResource {
    
    /**
     * Retrieves a customer by their email.
     *
     * @param email The email of the customer.
     * @return The customer with the given email, or null if no such customer exists.
     */
    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    /**
     * Adds a list of rooms to the hotel.
     *
     * @param rooms The list of rooms to be added.
     */
    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            ReservationService.addRoom(room);
        }
    }

    /**
     * Retrieves all rooms in the hotel.
     *
     * @return A collection of all rooms.
     */
    public static Collection<IRoom> getAllRooms() {
        return ReservationService.getAllRooms();
    }

    /**
     * Retrieves all customers of the hotel.
     *
     * @return A collection of all customers.
     */
    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    /**
     * Displays all reservations in the hotel.
     */
    public static void displayAllReservations() {
        ReservationService.printAllReservation();
    }
}
