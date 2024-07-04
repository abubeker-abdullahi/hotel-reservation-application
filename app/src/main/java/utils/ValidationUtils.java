package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

import model.Customer;
import model.IRoom;
import model.Room.RoomType;

/**
 * Utility class for validating various inputs related to hotel reservations.
 */
public class ValidationUtils {

    /**
     * Validates the format of an email address.
     *
     * @param email The email address to validate.
     * @throws IllegalArgumentException If the email format is invalid.
     */
    public static void emailValidation(String email) {
        final String EMAIL_PATTERN = "^(.+)@(.+).com$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("\u001B[31m" + "Invalid email format, please enter correct format for email.\n" + "\u001B[0m");
        }
    }

    /**
     * Validates that the check-in date is not before the check-out date or in the past.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @throws ParseException If there is an error parsing the dates.
     * @throws IllegalArgumentException If the check-in date is before today, the same as check-out date, or after check-out date.
     */
    public static void checkInAndCheckOutDateValidation(Date checkInDate, Date checkOutDate) throws ParseException {
        LocalDate today = LocalDate.now();
        LocalDate checkInLocalDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkOutLocalDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (checkInLocalDate.isBefore(today)) {
            throw new IllegalArgumentException("\u001B[31m" + "Check-in date cannot be in the past." + "\u001B[0m");

        } else if (checkInLocalDate.equals(checkOutLocalDate)) {
            throw new IllegalArgumentException("\u001B[31m" + "Check-in date cannot be the same as check-out date" + "\u001B[0m");

        } else if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("\u001B[31m" + "Check-in date must be before check-out date." + "\u001B[0m");
        }
    }

    /**
     * Validates if a given string date is in the correct format (MM/dd/yyyy) and converts it to a Date object.
     *
     * @param date  The date string to validate and convert.
     * @return      The parsed Date object.
     * @throws ParseException   If there is an error parsing the date.
     */
    public static Date dateFormatChecker(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateFormat = simpleDateFormat.parse(date);

        return dateFormat;
    }

    /**
     * Validates inputs for the Reservation class.
     *
     * @param room         The room object to validate.
     * @param customer     The customer object to validate.
     * @param checkInDate  The check-in date to validate.
     * @param checkOutDate The check-out date to validate.
     * @throws IllegalArgumentException If any of the inputs are null.
     */
    public static void reservationValidateInputs(IRoom room, Customer customer, Date checkInDate, Date checkOutDate) {
        if (room == null) {
            throw new IllegalArgumentException("\u001B[31m" + "Room must not be null." + "\u001B[0m");
        } else if (customer == null) {
            throw new IllegalArgumentException("\u001B[31m" + "Customer must not be null." + "\u001B[0m");
        } else if (checkInDate == null) {
            throw new IllegalArgumentException("\u001B[31m" + "Check-in date must not be null." + "\u001B[0m");
        } else if (checkOutDate == null) {
            throw new IllegalArgumentException("\u001B[31m" + "Check-out date must not be null." + "\u001B[0m");
        }
    }

    /**
     * Validates inputs for the Customer class.
     *
     * @param firstName The first name to validate.
     * @param lastName  The last name to validate.
     * @param email     The email address to validate.
     * @throws IllegalArgumentException If any of the inputs are null or empty.
     */
    public static void customerValidationInputs(String firstName, String lastName, String email) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("\u001B[31m" + "First name must not be empty or null." + "\u001B[0m");

        } else if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("\u001B[31m" + "Last name must not be empty or null." + "\u001B[0m");

        } else if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("\u001B[31m" + "Email must not be empty or null." + "\u001B[0m");
        }
    }

    /**
     * Validates inputs for the Room class.
     *
     * @param roomNumber The room number to validate.
     * @param roomPrice  The room price to validate.
     * @param roomType   The room type to validate.
     * @param isFree     The availability status of the room to validate.
     * @throws IllegalArgumentException If any of the inputs are null or empty.
     */
    public static void roomValidateInputs(String roomNumber, Double roomPrice, RoomType roomType, Boolean isFree) {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("\u001B[31m" + "Room number must not be empty or null." + "\u001B[0m");
        } else if (roomPrice == null) {
            throw new IllegalArgumentException("\u001B[31m" + "Room price must not be null." + "\u001B[0m");
        } else if (roomType == null) {
            throw new IllegalArgumentException("\u001B[31m" + "Room type must not be null." + "\u001B[0m");
        } else if (isFree == null) {
            throw new IllegalArgumentException("\u001B[31m" + "IsFree must not be null." + "\u001B[0m");
        }
    }
}
