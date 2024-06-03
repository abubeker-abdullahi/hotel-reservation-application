package api;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import model.Customer;
import model.Reservation;
import model.IRoom;

public class AdminResource {

    static final Collection<Customer> customersList = new HashSet<>();
    static final Collection<IRoom> roomsList = new HashSet<>();
    static final Collection<Reservation> reservationsList = new HashSet<>();

    public static Customer getCustomer(String email) {
        for (Customer customer : customersList) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            roomsList.add(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return new HashSet<>(roomsList);
    }

    public static Collection<Customer> getAllCustomers() {
        return new HashSet<>(customersList);
    }

    public static void displayAllReservation() {
        for (Reservation reservation : reservationsList) {
            System.out.println(reservation);
        }
    }
}

