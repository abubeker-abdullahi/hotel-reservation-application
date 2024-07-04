package userInterface;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import service.ReservationService;
import utils.ValidationUtils;

/**
 * Main menu class for the Hotel Reservation Application.
 * It provides methods to display and process the main menu options.
 */
public class MainMenu {

    /**
     * Main method to launch the application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        displayAndProcessMainMenu();
    }

    /**
     * Displays and processes the main menu options.
     */
    public static void displayAndProcessMainMenu() {
        boolean keepRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("\n");
                    System.out.println("Welcome to the Hotel Reservation Application");
                    System.out.println("--------------------------------------------");
                    System.out.println("1) Find and reserve a room");
                    System.out.println("2) See my reservation");
                    System.out.println("3) Create an Account");
                    System.out.println("4) Admin");
                    System.out.println("5) Exit");
                    System.out.println("--------------------------------------------");
                    System.out.println("Please select a number for the menu option");

                    int selection = Integer.parseInt(scanner.nextLine());

                    switch (selection) {
                        case 1:
                            findAndReserveRoom(scanner);
                            break;
                        case 2:
                            seeMyReservation(scanner);
                            break;  
                        case 3:
                            createAnAccount(scanner);
                            break;  
                        case 4:
                            displayAdminMenu();
                            break;  
                        case 5:
                            exitMainMenu();
                            break;
                        default:
                            System.out.println(selection + " is not a valid option. Please enter a number between 1 and 5.");
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println(ex + "\nPlease enter a number between 1 and 5");
                }

            }
        }
    }

    // selection 1: Find and reserve a room
    public static void findAndReserveRoom(Scanner scanner) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                System.out.println("\nEnter check in date mm/dd/yyyy example 06/15/2024");
                String checkInDateString = scanner.nextLine();
                Date checkInDate = ValidationUtils.dateFormatChecker(checkInDateString);
    
                System.out.println("\nEnter check out date mm/dd/yyyy example 06/20/2024");
                String checkOutDateString = scanner.nextLine();
                Date checkOutDate = ValidationUtils.dateFormatChecker(checkOutDateString);
    
                ValidationUtils.checkInAndCheckOutDateValidation(checkInDate, checkOutDate);
    
                Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);

                if (availableRooms.isEmpty()) {
                    System.out.println("\u001B[31m" + "\nThere is no room available for booking on the selected date." + "\u001B[0m");
                    
                    suggestAlternativeDates(scanner, checkInDate, checkOutDate);
                    keepRunning = false;

                } else {
                    System.out.println("\nAVAILABLE ROOMS:");

                    availableRooms.forEach(System.out::println);
                    System.out.println("\n");
    
                    System.out.println("Would you like to book a room? y/n");
                    String bookRoomSelection = scanner.nextLine();
                                
                    if (bookRoomSelection.equalsIgnoreCase("y")) {
                        System.out.println("\nDo you have an account with us? y/n");
                        String customerAccountSelection = scanner.nextLine();
                        
                        if (customerAccountSelection.equalsIgnoreCase("y")) {
                            System.out.println("Enter Email Address (Format: name@domain.com)");
                            String email = scanner.nextLine();
                            ValidationUtils.emailValidation(email);
    
                            if (HotelResource.getCustomer(email) == null) {
                                throw new IllegalArgumentException("\u001B[31m" + "There is no account with this email: " + email + "\u001B[0m");
                            } else {
                                System.out.println("\nAVAILABLE ROOMS:");
                                
                                availableRooms.forEach(System.out::println);
                                System.out.println("\n");

                                System.out.println("What room would you like to reserve?:");
                                String roomNumber = scanner.nextLine();
                                
                                IRoom iRoomNumber = HotelResource.getRoom(roomNumber);
                                if (iRoomNumber != null && ReservationService.isRoomAvailable(iRoomNumber, checkInDate, checkOutDate)) {
                                    HotelResource.bookARoom(email, iRoomNumber, checkInDate, checkOutDate);
                                    System.out.println("\u001B[32m" + "Room booked successfully" + "\u001B[0m");

                                    System.out.println(HotelResource.bookARoom(email, iRoomNumber, checkInDate, checkOutDate));
                                    System.out.println("\n");
                                    
                                    keepRunning = false;
                                } else {
                                    System.out.println("The room is not available");
                                    keepRunning = false;
                                }
                            }
                        } else if (customerAccountSelection.equalsIgnoreCase("n")) {
                            createAnAccount(scanner);

                            System.out.println("\nEnter Email Address (Format: name@domain.com)");
                            String email = scanner.nextLine();
                            ValidationUtils.emailValidation(email);

                            System.out.println("What room would you like to reserve?:");
                            String roomNumber = scanner.nextLine();

                            IRoom room = HotelResource.getRoom(roomNumber);

                            if (room != null && ReservationService.isRoomAvailable(room, checkInDate, checkOutDate)) {
                                HotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                                System.out.println("\u001B[32m" + "Room booked successfully" + "\u001B[0m");
                                System.out.println(HotelResource.bookARoom(email, room, checkInDate, checkOutDate));
                                
                                keepRunning = false;

                            } else {
                                System.out.println("The room is not available");
                                keepRunning = false;
                            }

                        }
                    } else if (bookRoomSelection.equalsIgnoreCase("n")) {
                        keepRunning = false;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                keepRunning = false;
            }
        }
    }
    

    // selection 2: See my reservation
    public static void seeMyReservation(Scanner scanner) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                System.out.println("\nEnter Email Address (Format: name@domain.com)");
                String email = scanner.nextLine();
                ValidationUtils.emailValidation(email);
                
                if (HotelResource.getCustomerReservations(email) == null || HotelResource.getCustomerReservations(email).isEmpty()) {
                    System.out.println("\u001B[31m" + "\nThere is no reservation for email: " + email + "\u001B[0m");
                } else {
                    System.out.println("\n");
                    System.out.println(HotelResource.getCustomerReservations(email));
                }
                keepRunning = false;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    // selection 3: Create an account
    public static void createAnAccount(Scanner scanner) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                System.out.println("\nEnter Email Address (Format: name@domain.com)");
                String email = scanner.nextLine();
                ValidationUtils.emailValidation(email);
                
                if(AdminResource.getCustomer(email) != null) {
                    throw new IllegalArgumentException("\u001B[31m" + email + " is already in use." + "\u001B[0m");
                }
                
                System.out.println("\nFirst Name:");
                String firstName = scanner.nextLine();

                System.out.println("\nLast Name:");
                String lastName = scanner.nextLine();
                
                HotelResource.createACustomer(email, firstName, lastName);

                System.out.println("\n" + "\u001B[32m" + "Account created successfully." + "\u001B[0m");
                System.out.println(HotelResource.getCustomer(email));
                
                keepRunning = false;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    // selection 4: Admin
    public static void displayAdminMenu() {
        AdminMenu.displayAndProcessAdminMenu();
    }

    // selection 5: Exit
    public static void exitMainMenu() {
        System.out.println("Exiting ...");
        System.exit(0);
    }

    /**
     * Suggests alternative dates for room reservation.
     * @param scanner the scanner to read user input
     * @param originalCheckIn the original check-in date
     * @param originalCheckOut the original check-out date
     * @throws ParseException if the dates cannot be parsed
     */
    private static void suggestAlternativeDates(Scanner scanner, Date originalCheckIn, Date originalCheckOut) throws ParseException {

        Date alternativeCheckIn = new Date(originalCheckIn.getTime());
        Date alternativeCheckOut = new Date(originalCheckOut.getTime());
        
        boolean foundAlternative = false;
        int alternateDaysToLook = 7;
    
        for (int i=1; i<=alternateDaysToLook; i++) {
    
            alternativeCheckIn = new Date(originalCheckIn.getTime() + (1000 * 60 * 60 * 24 * i));
            alternativeCheckOut = new Date(originalCheckOut.getTime() + (1000 * 60 * 60 * 24 * i));

            Collection<IRoom> availableRooms = HotelResource.findARoom(alternativeCheckIn, alternativeCheckOut);
    
            if (!availableRooms.isEmpty()) {
                foundAlternative = true;
                
                System.out.println("\u001B[33m" + "\nAlternative available dates found: " + "\u001B[0m");
                System.out.println("Check-in: " + alternativeCheckIn);
                System.out.println("Check-out: " + alternativeCheckOut);
    
                System.out.println("\nAVAILABLE ROOMS:");
    
                availableRooms.forEach(System.out::println);
    
                System.out.println("\nWould you like to book a room for these dates? y/n");
                String bookRoomSelection = scanner.nextLine();
    
                if (bookRoomSelection.equalsIgnoreCase("y")) {
                    System.out.println("\nDo you have an account with us? y/n");
                    String customerAccountSelection = scanner.nextLine();
    
                    if (customerAccountSelection.equalsIgnoreCase("y")) {
                        System.out.println("Enter Email Address (Format: name@domain.com)");
                        String email = scanner.nextLine();
    
                        ValidationUtils.emailValidation(email);
    
                        if (HotelResource.getCustomer(email) == null) {
                            System.out.println("\u001B[31m" + "There is no account with this email: " + email + "\u001B[0m");
    
                        } else {
    
                            System.out.println("\nAVAILABLE ROOMS:");
                            availableRooms.forEach(System.out::println);
                            System.out.println("\n");
    
                            System.out.println("What room would you like to reserve?:");
                            String roomNumber = scanner.nextLine();
    
                            IRoom iRoomNumber = HotelResource.getRoom(roomNumber);
    
                            if (iRoomNumber != null && ReservationService.isRoomAvailable(iRoomNumber, alternativeCheckIn, alternativeCheckOut)) {
    
                                HotelResource.bookARoom(email, iRoomNumber, alternativeCheckIn, alternativeCheckOut);
                                System.out.println("\u001B[32m" + "Room booked successfully" + "\u001B[0m");
    
                                System.out.println(HotelResource.bookARoom(email, iRoomNumber, alternativeCheckIn, alternativeCheckOut));
                                return;
    
                            } else {
                                System.out.println("\u001B[31m" + "The room is not available" + "\u001B[0m");
                            }
                        }
                    } else if (customerAccountSelection.equalsIgnoreCase("n")) {

                        createAnAccount(scanner);

                        System.out.println("\nEnter Email Address (Format: name@domain.com)");
                        String email = scanner.nextLine();
    
                        ValidationUtils.emailValidation(email);
    
                        System.out.println("What room would you like to reserve?:");
                        String roomNumber = scanner.nextLine();
    
                        IRoom room = HotelResource.getRoom(roomNumber);
    
                        if (room != null && ReservationService.isRoomAvailable(room, alternativeCheckIn, alternativeCheckOut)) {
    
                            HotelResource.bookARoom(email, room, alternativeCheckIn, alternativeCheckOut);
    
                            System.out.println("\u001B[32m" + "Room booked successfully" + "\u001B[0m");
                            System.out.println(HotelResource.bookARoom(email, room, alternativeCheckIn, alternativeCheckOut));
    
                            return;

                        } else {
                            System.out.println("\u001B[31m" + "The room is not available" + "\u001B[0m");
                        }
                    }
                }
            }
        }
        if (!foundAlternative) {
            System.out.println("\u001B[31m" + "No rooms available within " + alternateDaysToLook + " days after the selected dates." + "\u001B[0m");
        }
    }
}
