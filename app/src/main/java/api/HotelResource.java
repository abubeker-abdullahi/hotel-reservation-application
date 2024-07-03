package api;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

/**
 * The HotelResource class provides an API for accessing hotel-related services.
 * This includes functionalities for retrieving customers, rooms, and reservations.
 */
public class HotelResource {

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
     * Creates a new customer.
     *
     * @param email     The email of the new customer.
     * @param firstName The first name of the new customer.
     * @param lastName  The last name of the new customer.
     */
    public static void createACustomer(String email, String firstName, String lastName) {
       CustomerService.addCustomer(email, firstName, lastName);
    }

    /**
     * Retrieves a room by its room number.
     *
     * @param roomNumber The room number.
     * @return The room with the given room number, or null if no such room exists.
     */
    public static IRoom getRoom(String roomNumber) {
        return ReservationService.getARoom(roomNumber);
    }

    /**
     * Books a room for a customer.
     *
     * @param customerEmail The email of the customer.
     * @param room          The room to be booked.
     * @param checkInDate   The check-in date.
     * @param checkOutDate  The check-out date.
     * @return The reservation details, or null if the room cannot be booked.
     * @throws ParseException if there is an error parsing the date.
     */
    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws ParseException {
        Customer customer = getCustomer(customerEmail);

        return ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    /**
     * Retrieves all reservations for a given customer.
     *
     * @param customerEmail The email of the customer.
     * @return A collection of reservations for the customer, or an empty collection if none exist.
     */
    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        return ReservationService.getCustomerReservation(getCustomer(customerEmail));
    }

    /**
     * Finds available rooms for a given date range.
     *
     * @param checkIn  The check-in date.
     * @param checkOut The check-out date.
     * @return A collection of available rooms for the specified date range.
     */
    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return ReservationService.findRooms(checkIn, checkOut);
    }
}
