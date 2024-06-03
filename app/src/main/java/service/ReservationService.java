package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Customer;
import model.IRoom;
import model.Reservation;

public class ReservationService {
    private static final Map<String, IRoom> roomsList = new HashMap<>();
    private static final Set<Reservation> reservationList = new HashSet<>();
    private static final Set<IRoom> allAvailableRooms = new HashSet<>();
    

    public static void addRoom(IRoom room) {
        roomsList.put(room.getRoomNumber(), room);
        if (room.isFree()) {
            allAvailableRooms.add(room);
        }
    }
    
    public static IRoom getARoom(String roomId) {
        return roomsList.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (customer == null || room == null || checkInDate == null || checkInDate == null) {
            throw new IllegalArgumentException("customer, room, check-in date and check-out date must not be null");
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }

        if (!room.isFree()) {
            throw new IllegalArgumentException("The room is not free and cannot be booked");
        }

        Reservation reservation = new Reservation(room, customer, checkInDate, checkOutDate);
        reservationList.add(reservation);

        if (room instanceof model.Room) {
            ((model.Room) room).setIsFree(false);
        } else if (room  instanceof model.FreeRoom) {
            ((model.FreeRoom) room).setIsFree(false);
        }
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Check-in and check-out date must not be null");
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }

        List<IRoom> availableRooms = new ArrayList<>();

        for (IRoom room: roomsList.values()) {
            if(isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }

        return availableRooms; 
    }

    public static Collection<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> customerReservation = new ArrayList<>();
        for (Reservation reservation:reservationList) {
            if(reservation.getCustomer().equals(customer)) {
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    public static void printAllReservation() {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservationList) {
            System.out.println(reservation);
        }
    }

    public static boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : reservationList) {
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