package userInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import api.AdminResource;
import model.IRoom;
import model.Room;

/**
 * The AdminMenu class provides the user interface for administrative tasks.
 * It allows the admin to see all customers, rooms, reservations, add new rooms,
 * and add test data.
 */
public class AdminMenu {

    /**
     * Displays and processes the admin menu options.
     */
    public static void displayAndProcessAdminMenu() {
        boolean keepRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("\n");
                    System.out.println("Admin Menu");
                    System.out.println("--------------------------------------------");
                    System.out.println("1) See all Customers");
                    System.out.println("2) See all Rooms");
                    System.out.println("3) See all Reservations");
                    System.out.println("4) Add a Room");
                    System.out.println("5) Add Test Data");
                    System.out.println("6) Back to Main Menu");
                    System.out.println("--------------------------------------------");
                    System.out.println("Please select a number for the menu option");

                    int selection = Integer.parseInt(scanner.nextLine());

                    switch (selection) {
                        case 1:
                            seeAllCustomers();
                            break;
                        case 2:
                            seeAllRooms();
                            break;
                        case 3:
                            seeAllReservations();
                            break;
                        case 4:
                            addRoom(scanner);
                            break;
                        case 5:
                            addTestData();
                            break;
                        case 6:
                            backToMainMenu();
                            break;
                        default:
                            System.out.println(selection + " is an invalid Input, please enter a number between 1 and 6");
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println(ex + ". please enter a number between 1 and 6");
                }
            }
        }
    }

    /**
     * Displays all customers.
     */
    public static void seeAllCustomers() {
        if (AdminResource.getAllCustomers() == null || AdminResource.getAllCustomers().isEmpty()) {
            System.out.println("\n" + "\u001B[33m" + "THERE IS NO CUSTOMER ON RECORD." + "\u001B[0m");
        } else {
            System.out.println("\u001B[32m" + "\nALL CUSTOMERS:" + "\u001B[0m");
            AdminResource.getAllCustomers().forEach(System.out::println);
        }
    }

    /**
     * Displays all rooms.
     */
    public static void seeAllRooms() {
        if (AdminResource.getAllRooms() == null || AdminResource.getAllRooms().isEmpty()) {
            System.out.println("\n" + "\u001B[33m" + "THERE IS NO ROOM ON RECORD." + "\u001B[0m");
        } else {
            System.out.println("\u001B[32m" + "\nALL ROOMS" + "\u001B[0m");
            AdminResource.getAllRooms().forEach(System.out::println);
        }
    }

    /**
     * Displays all reservations.
     */
    public static void seeAllReservations() {
        AdminResource.displayAllReservations();
    }

    /**
     * Adds a new room to the system.
     *
     * @param scanner The Scanner object for user input.
     */
    public static void addRoom(Scanner scanner) {
        boolean keepRunning = true;
        Set<IRoom> rooms = new HashSet<>();
        while (keepRunning) {
            try {
                System.out.println("\nEnter room number:");
                String roomNumber = scanner.nextLine();

                System.out.println("\nEnter price per night:");
                double pricePerNight = Double.parseDouble(scanner.nextLine());

                System.out.println("\nEnter room type: 1 for single bed, 2 for double bed");
                int roomTypeSelection = Integer.parseInt(scanner.nextLine());

                Room.RoomType roomType;
                if (roomTypeSelection == 1) {
                    roomType = Room.RoomType.SINGLE;
                } else if (roomTypeSelection == 2) {
                    roomType = Room.RoomType.DOUBLE;
                } else {
                    throw new IllegalArgumentException("\u001B[31m" + "Invalid room type selection. Please enter 1 for single bed or 2 for double bed." + "\u001B[0m");
                }

                Room room = new Room(roomNumber, pricePerNight, roomType, true);

                if (rooms.contains(room)) {
                    System.out.println("\nRoom #" + roomNumber + " already exists. Do you want to override it? (y/n)");
                    String overrideSelection = scanner.nextLine();

                    if (overrideSelection.equalsIgnoreCase("y")) {
                        rooms.remove(room);
                        rooms.add(room);
                        System.out.println("\nRoom # " + roomNumber + " has been overridden.\n");
                    } else if (overrideSelection.equalsIgnoreCase("n")) {
                        System.out.println("\nRoom # " + roomNumber + " was not overridden.\n");
                    } else {
                        System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                        continue;
                    }
                } else {
                    rooms.add(room);
                    System.out.println("\n" + room);
                    System.out.println("\u001B[32m" + "ROOM ADDED SUCCESSFULLY\n" + "\u001B[0m");
                }

                while (true) {
                    System.out.println("Would you like to add another room? (y/n)");
                    String addAnotherRoomSelection = scanner.nextLine();
                    if (addAnotherRoomSelection.equalsIgnoreCase("y")) {
                        break;
                    } else if (addAnotherRoomSelection.equalsIgnoreCase("n")) {
                        AdminResource.addRoom(new ArrayList<>(rooms));
                        keepRunning = false;
                        break;
                    } else {
                        System.out.println("\nInvalid input: " + addAnotherRoomSelection + "\nPlease enter either 'y' for yes or 'n' for no.\n");
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n");
                System.out.println("\u001B[31m" + ex + "\u001B[0m");
                System.out.println("\n");
            } catch (IllegalArgumentException ex) {
                System.out.println("\u001B[31m" + ex.getMessage() + "\u001B[0m");
            }
        }
    }

    /**
     * Adds test data to the system.
     */
    public static void addTestData() {
        boolean keepRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("\n");
                    System.out.println("Add Test Data");
                    System.out.println("--------------------------------------------");
                    System.out.println("1) Add Customers");
                    System.out.println("2) Add Rooms");
                    System.out.println("3) Back to Admin Menu\n");
                    System.out.println("Please select a number for the menu option");

                    int testDataSelection = Integer.parseInt(scanner.nextLine());

                    switch (testDataSelection) {
                        case 1:
                            MainMenu.createAnAccount(scanner);
                            break;
                        case 2:
                            AdminMenu.addRoom(scanner);
                            break;
                        case 3:
                            AdminMenu.displayAndProcessAdminMenu();
                            break;
                        default:
                            System.out.println(testDataSelection + " is not a valid option. Please enter a number between 1 and 2.");
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("\nPlease enter a number between 1 and 2 to add test data");
                }
            }
        }
    }

    /**
     * Goes back to the main menu.
     */
    public static void backToMainMenu() {
        MainMenu.displayAndProcessMainMenu();
    }
}
