package model;

import java.text.ParseException;
import java.util.Date;

import utils.ValidationUtils;

/**
 * The Reservation class represents a hotel reservation, including the room, customer, and the check-in and check-out dates.
 */
public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    /**
     * Constructs a new Reservation with the specified room, customer, check-in date, and check-out date.
     * 
     * @param room the room for the reservation
     * @param customer the customer making the reservation
     * @param checkInDate the check-in date for the reservation
     * @param checkOutDate the check-out date for the reservation
     * @throws ParseException 
     * @throws IllegalArgumentException if any argument is null or if the check-in date is after the check-out date
     */
    public Reservation(IRoom room, Customer customer, Date checkInDate, Date checkOutDate) throws ParseException {
        super();

        ValidationUtils.reservationValidateInputs(room, customer, checkInDate, checkOutDate); // validates non-null inputs
        ValidationUtils.checkInAndCheckOutDateValidation(checkInDate, checkOutDate); // validates check-in date is not after check-out date

        this.room = room;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    /**
     * Gets the room for this reservation.
     * 
     * @return the room
     */
    public IRoom getRoom() {
        return room;
    }

    /**
     * Gets the customer for this reservation.
     * 
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the check-in date for this reservation.
     * 
     * @return the check-in date
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Gets the check-out date for this reservation.
     * 
     * @return the check-out date
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Sets the room for this reservation.
     * 
     * @param room the new room
     */
    public void setRoom(IRoom room) {
        this.room = room;
    }

    /**
     * Sets the customer for this reservation.
     * 
     * @param customer the new customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Sets the check-in date for this reservation.
     * 
     * @param checkInDate the new check-in date
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Sets the check-out date for this reservation.
     * 
     * @param checkOutDate the new check-out date
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((checkInDate == null) ? 0 : checkInDate.hashCode());
        result = prime * result + ((checkOutDate == null) ? 0 : checkOutDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (checkInDate == null) {
            if (other.checkInDate != null)
                return false;
        } else if (!checkInDate.equals(other.checkInDate))
            return false;
        if (checkOutDate == null) {
            if (other.checkOutDate != null)
                return false;
        } else if (!checkOutDate.equals(other.checkOutDate))
            return false;
        return true;
    }

    /**
     * Returns a string representation of this reservation.
     * 
     * @return a string representation of this reservation
     */
    @Override
    public String toString() {
        return "Reservation {room=" + room + ", \ncustomer=" + customer + ", \ncheckInDate=" + checkInDate + ", \ncheckOutDate=" + checkOutDate + "}\n";
    }
}
