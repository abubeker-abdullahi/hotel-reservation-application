package utils;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import model.Customer;
import model.IRoom;
import model.Room.RoomType;

public class ValidationUtilsTest {

    /**
     * Tests a valid email address.
     */
    @Test
    public void testEmailValidation_ValidEmail() {
        String validEmail = "abubeker@email.com";
        ValidationUtils.emailValidation(validEmail);
    }

    /**
     * Tests an invalid email address and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmailValidation_InvalidEmail() {
        String invalidEmail = "invalid-email";
        ValidationUtils.emailValidation(invalidEmail);
    }

    /**
     * Tests valid check-in and check-out dates.
     */
    @Test
    public void testCheckInAndCheckOutDateValidation_ValidDates() throws ParseException {
        Date checkInDate = ValidationUtils.dateFormatChecker("12/20/2024");
        Date checkOutDate = ValidationUtils.dateFormatChecker("12/25/2024");
        ValidationUtils.checkInAndCheckOutDateValidation(checkInDate, checkOutDate);
    }

    /**
     * Tests a past check-in date and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInAndCheckOutDateValidation_PastCheckInDate() throws ParseException {
        Date checkInDate = ValidationUtils.dateFormatChecker("01/01/2020");
        Date checkOutDate = ValidationUtils.dateFormatChecker("12/25/2024");
        ValidationUtils.checkInAndCheckOutDateValidation(checkInDate, checkOutDate);
    }

    /**
     * Tests identical check-in and check-out dates and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInAndCheckOutDateValidation_SameCheckInAndCheckOutDate() throws ParseException {
        Date checkInDate = ValidationUtils.dateFormatChecker("12/25/2024");
        Date checkOutDate = ValidationUtils.dateFormatChecker("12/25/2024");
        ValidationUtils.checkInAndCheckOutDateValidation(checkInDate, checkOutDate);
    }

    /**
     * Tests a check-in date after the check-out date and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInAndCheckOutDateValidation_CheckInAfterCheckOut() throws ParseException {
        Date checkInDate = ValidationUtils.dateFormatChecker("12/26/2024");
        Date checkOutDate = ValidationUtils.dateFormatChecker("12/25/2024");
        ValidationUtils.checkInAndCheckOutDateValidation(checkInDate, checkOutDate);
    }

    /**
     * Tests a valid date string.
     */
    @Test
    public void testDateFormatChecker_ValidDate() throws ParseException {
        String date = "12/25/2024";
        Date parsedDate = ValidationUtils.dateFormatChecker(date);
        assertNotNull(parsedDate);
    }

    /**
     * Tests an invalid date string and expects a ParseException.
     */
    @Test(expected = ParseException.class)
    public void testDateFormatChecker_InvalidDate() throws ParseException {
        String invalidDate = "2024-12-25";
        ValidationUtils.dateFormatChecker(invalidDate);
    }

    /**
     * Tests valid inputs for the reservation validation.
     */
    @Test
    public void testReservationValidateInputs_ValidInputs() {
        IRoom room = new IRoom() {
            @Override
            public String getRoomNumber() { return "101"; }
            @Override
            public Double getRoomPrice() { return 100.0; }
            @Override
            public RoomType getRoomType() { return RoomType.SINGLE; }
            @Override
            public boolean isFree() { return true; }
        };
        Customer customer = new Customer("Abubeker", "Abdullahi", "abubeker.abdullahi@email.com");
        Date checkInDate = new Date();
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000); // 1 day later

        ValidationUtils.reservationValidateInputs(room, customer, checkInDate, checkOutDate);
    }

    /**
     * Tests null room input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReservationValidateInputs_NullRoom() {
        Customer customer = new Customer("Abubeker", "Abdullahi", "abubeker.abdullahi@email.com");
        Date checkInDate = new Date();
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000); // 1 day later

        ValidationUtils.reservationValidateInputs(null, customer, checkInDate, checkOutDate);
    }

    /**
     * Tests null customer input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReservationValidateInputs_NullCustomer() {
        IRoom room = new IRoom() {
            @Override
            public String getRoomNumber() { return "101"; }
            @Override
            public Double getRoomPrice() { return 100.0; }
            @Override
            public RoomType getRoomType() { return RoomType.SINGLE; }
            @Override
            public boolean isFree() { return true; }
        };
        Date checkInDate = new Date();
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000); // 1 day later

        ValidationUtils.reservationValidateInputs(room, null, checkInDate, checkOutDate);
    }

    /**
     * Tests null check-in date input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReservationValidateInputs_NullCheckInDate() {
        IRoom room = new IRoom() {
            @Override
            public String getRoomNumber() { return "101"; }
            @Override
            public Double getRoomPrice() { return 100.0; }
            @Override
            public RoomType getRoomType() { return RoomType.SINGLE; }
            @Override
            public boolean isFree() { return true; }
        };
        Customer customer = new Customer("Abubeker", "Abdullahi", "abubeker.abdullahi@email.com");
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000); // 1 day later

        ValidationUtils.reservationValidateInputs(room, customer, null, checkOutDate);
    }

    /**
     * Tests null check-out date input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReservationValidateInputs_NullCheckOutDate() {
        IRoom room = new IRoom() {
            @Override
            public String getRoomNumber() { return "101"; }
            @Override
            public Double getRoomPrice() { return 100.0; }
            @Override
            public RoomType getRoomType() { return RoomType.SINGLE; }
            @Override
            public boolean isFree() { return true; }
        };
        Customer customer = new Customer("Abubeker", "Abdullahi", "abubeker.abdullahi@email.com");
        Date checkInDate = new Date();

        ValidationUtils.reservationValidateInputs(room, customer, checkInDate, null);
    }

    /**
     * Tests valid inputs for the customer validation.
     */
    @Test
    public void testCustomerValidationInputs_ValidInputs() {
        ValidationUtils.customerValidationInputs("Abubeker", "Abdullahi", "abubeker.abdullahi@email.com");
    }

    /**
     * Tests null first name input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCustomerValidationInputs_NullFirstName() {
        ValidationUtils.customerValidationInputs(null, "Abdullahi", "abubeker.abdullahi@email.com");
    }

    /**
     * Tests empty first name input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCustomerValidationInputs_EmptyFirstName() {
        ValidationUtils.customerValidationInputs("", "Abdullahi", "abubeker.abdullahi@email.com");
    }

    /**
     * Tests null last name input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCustomerValidationInputs_NullLastName() {
        ValidationUtils.customerValidationInputs("Abubeker", null, "abubeker.abdullahi@email.com");
    }

    /**
     * Tests empty last name input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCustomerValidationInputs_EmptyLastName() {
        ValidationUtils.customerValidationInputs("Abubeker", "", "abubeker.abdullahi@email.com");
    }

    /**
     * Tests null email input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCustomerValidationInputs_NullEmail() {
        ValidationUtils.customerValidationInputs("Abubeker", "Abdullahi", null);
    }

    /**
     * Tests empty email input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCustomerValidationInputs_EmptyEmail() {
        ValidationUtils.customerValidationInputs("Abubeker", "Abdullahi", "");
    }

    /**
     * Tests valid inputs for the room validation.
     */
    @Test
    public void testRoomValidateInputs_ValidInputs() {
        ValidationUtils.roomValidateInputs("101", 100.0, RoomType.SINGLE, true);
    }

    /**
     * Tests null room number input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoomValidateInputs_NullRoomNumber() {
        ValidationUtils.roomValidateInputs(null, 100.0, RoomType.SINGLE, true);
    }

    /**
     * Tests empty room number input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoomValidateInputs_EmptyRoomNumber() {
        ValidationUtils.roomValidateInputs("", 100.0, RoomType.SINGLE, true);
    }

    /**
     * Tests null room price input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoomValidateInputs_NullRoomPrice() {
        ValidationUtils.roomValidateInputs("101", null, RoomType.SINGLE, true);
    }

    /**
     * Tests null room type input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoomValidateInputs_NullRoomType() {
        ValidationUtils.roomValidateInputs("101", 100.0, null, true);
    }

    /**
     * Tests null isFree input and expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoomValidateInputs_NullIsFree() {
        ValidationUtils.roomValidateInputs("101", 100.0, RoomType.SINGLE, null);
    }
}
