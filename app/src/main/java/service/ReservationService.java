package service;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import model.Customer;
import model.IRoom;
import model.Reservation;

/**
 * ReservationService class provides methods to manage rooms and reservations in the hotel reservation system.
 * It allows adding rooms, reserving rooms, finding available rooms, and retrieving customer reservations.
 */
public class ReservationService {

    private static final Set<IRoom> allRooms = new HashSet<>();
    private static final Set<Reservation> allReservations = new HashSet<>();

    /**
     * Adds a new room to the system.
     *
     * @param room The room to be added.
     */
    public static void addRoom(IRoom room) {
        allRooms.add(room);
    }

    /**
     * Retrieves a room by its room number.
     *
     * @param roomId The room number of the room.
     * @return The IRoom object if found, otherwise null.
     */
    public static IRoom getARoom(String roomId) {
        for (IRoom room : allRooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Reserves a room for a customer.
     *
     * @param customer     The customer making the reservation.
     * @param room         The room to be reserved.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return The Reservation object for the reserved room.
     * @throws ParseException If there is an error parsing the dates.
     */
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) throws ParseException {
        Reservation reservation = new Reservation(room, customer, checkInDate, checkOutDate);
        allReservations.add(reservation);

        if (room instanceof model.Room) {
            ((model.Room) room).setIsFree(false);
        } else if (room instanceof model.FreeRoom) {
            ((model.FreeRoom) room).setIsFree(false);
        }
        return reservation;
    }

    /**
     * Finds available rooms for the specified check-in and check-out dates.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return A collection of available rooms.
     */
    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return allRooms.stream()
                        .filter(room -> isRoomAvailable(room, checkInDate, checkOutDate))
                        .collect(Collectors.toList());
    }

    /**
     * Retrieves all reservations for a specific customer.
     *
     * @param customer The customer whose reservations are to be retrieved.
     * @return A collection of reservations for the customer.
     */
    public static Collection<Reservation> getCustomerReservation(Customer customer) {
        return allReservations.stream()
                              .filter(reservation -> reservation.getCustomer().equals(customer))
                              .collect(Collectors.toList());
    }

    /**
     * Prints all reservations in the system.
     */
    public static void printAllReservation() {
        if (allReservations.isEmpty()) {
            System.out.println("\n" + "\u001B[33m" + "THERE IS NO RESERVATION ON RECORD." + "\u001B[0m");
        } else {
            System.out.println("\u001B[32m" + "\nALL RESERVATIONS:" + "\u001B[0m");
            for (Reservation reservation : allReservations) {
                System.out.println(reservation);
            }
        }
    }

    /**
     * Retrieves all rooms in the system.
     *
     * @return A collection of all rooms.
     */
    public static Collection<IRoom> getAllRooms() {
        return new HashSet<>(allRooms);
    }

    /**
     * Checks if a room is available for the specified check-in and check-out dates.
     *
     * @param room         The room to be checked.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return True if the room is available, otherwise false.
     */
    public static boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : allReservations) {
            if (reservation.getRoom().equals(room)) {
                Date reservationCheckInDate = reservation.getCheckInDate();
                Date reservationCheckOutDate = reservation.getCheckOutDate();

                if (checkInDate.before(reservationCheckOutDate) && checkOutDate.after(reservationCheckInDate)) {
                    return false;
                }
            }
        }
        return true;
    }
}
