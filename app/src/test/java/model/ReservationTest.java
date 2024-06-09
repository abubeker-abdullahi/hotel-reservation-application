package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Room.RoomType;

/**
 * Test class for the {@link model.Reservation} class.
 */
public class ReservationTest {

    private SimpleDateFormat dateFormat;
    private IRoom room;
    private Customer customer;
    private Date checkInDate;
    private Date checkOutDate;

    /**
     * Sets up the test data before each test.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @BeforeEach
    private void setUp() throws ParseException {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        room = new Room("100", 200.5, RoomType.DOUBLE, false);
        customer = new Customer("Abubeker", "Abdullahi", "abubeker@email.com");
        checkInDate = dateFormat.parse("06/15/2024");
        checkOutDate = dateFormat.parse("06/20/2024");
    }

    /**
     * Tests the setter and getter methods of the {@link model.Reservation} class.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    private void testReservationSetterAndGetter() throws ParseException {
        Reservation reservation = new Reservation(room, customer, checkInDate, checkOutDate);

        reservation.setRoom(room);
        reservation.setCustomer(customer);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);

        assertEquals(room.toString(), reservation.getRoom().toString());
        assertEquals(customer.toString(), reservation.getCustomer().toString());
        assertEquals(checkInDate.toString(), reservation.getCheckInDate().toString());
        assertEquals(checkOutDate.toString(), reservation.getCheckOutDate().toString());
    }

    /**
     * Tests that a null room throws an {@link IllegalArgumentException}.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    private void testNullRoom() throws ParseException {
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(null, customer, checkInDate, checkOutDate);
        });
    }

    /**
     * Tests that a null customer throws an {@link IllegalArgumentException}.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    private void testNullCustomer() throws ParseException {
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(room, null, checkInDate, checkOutDate);
        });
    }

    /**
     * Tests that a null check-in date throws an {@link IllegalArgumentException}.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    private void testNullCheckInDate() throws ParseException {
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(room, customer, null, checkOutDate);
        });
    }

    /**
     * Tests that a null check-out date throws an {@link IllegalArgumentException}.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    private void testNullCheckOutDate() throws ParseException {
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(room, customer, checkInDate, null);
        });
    }

    /**
     * Tests the {@link model.Reservation#toString()} method.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    private void testReservationToString() throws ParseException {
        Reservation reservation = new Reservation(room, customer, checkInDate, checkOutDate);

        String expected = "Reservation {room=Room {roomNumber=100, roomPrice=$200.5, roomType=DOUBLE, isFree=false}, " +
                          "customer=Customer {firstName=Abubeker, lastName=Abdullahi, email=abubeker@email.com}, " +
                          "checkInDate=Sat Jun 15 00:00:00 UTC 2024, " +
                          "checkOutDate=Thu Jun 20 00:00:00 UTC 2024}";

        assertEquals(expected, reservation.toString());
    }

    /**
     * Tests that the check-in date cannot be after the check-out date.
     * 
     * @throws ParseException if there is an error parsing the date
     */
    @Test
    public void testCheckInDateValidation() throws ParseException {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date checkInDate2 = dateFormat.parse("06/20/2024");
        Date checkOutDate2 = dateFormat.parse("06/15/2024");

        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(room, customer, checkInDate2, checkOutDate2);
        });
    }
}
