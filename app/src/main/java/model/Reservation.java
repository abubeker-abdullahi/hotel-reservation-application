package model;

import java.util.Date;

public class Reservation {
    public Customer customer;
    public IRoom room;
    public Date checkInDate;
    public Date checkOutDate;
    
    public Reservation(IRoom room, Customer customer, Date checkInDate, Date checkOutDate) {
        super();

        checkInValidation(checkInDate, checkOutDate); //Validates check-in date against check-out date.

        this.room = room;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation [room=" + room + ", customer=" + customer + ", checkInDate=" + checkInDate
                + ", checkOutDate=" + checkOutDate + "]";
    }

    // Validates check-in date against check-out date.
    public void checkInValidation(Date checkInDate, Date checkOutDate) {
        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }
    }
}
