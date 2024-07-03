package model;

import java.util.Objects;

import utils.ValidationUtils;

/**
 * The Room class represents a hotel room with its number, price, type, and availability.
 */
public class Room implements IRoom {
    /**
     * The RoomType enumeration represents the type of a room.
     */
    public enum RoomType {
        SINGLE, DOUBLE;
    }

    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    private Boolean isFree;

    /**
     * Constructs a new Room with the specified room number, price, type, and availability.
     * 
     * @param roomNumber the room number
     * @param roomPrice the room price
     * @param roomType the room type
     * @param isFree the availability of the room
     * @throws IllegalArgumentException if any argument is empty or null
     */
    public Room(String roomNumber, Double roomPrice, RoomType roomType, Boolean isFree) {
        super();
        
        ValidationUtils.roomValidateInputs(roomNumber, roomPrice, roomType, isFree); // validates non-null inputs

        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = isFree;
    }
    
    /**
     * Gets the room number.
     * 
     * @return the room number
     */
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Gets the room price.
     * 
     * @return the room price
     */
    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    /**
     * Gets the room type.
     * 
     * @return the room type
     */
    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Checks if the room is free.
     * 
     * @return true if the room is free, false otherwise
     */
    @Override
    public boolean isFree() {
        return isFree;
    }
    
    /**
     * Sets the room number.
     * 
     * @param roomNumber the new room number
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Sets the room price.
     * 
     * @param roomPrice the new room price
     */
    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    /**
     * Sets the room type.
     * 
     * @param roomType the new room type
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Sets the availability of the room.
     * 
     * @param isFree the new availability
     */
    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    /**
     * Returns a string representation of the room.
     * 
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return "Room {roomNumber=" + roomNumber + ", roomPrice=$" + roomPrice + ", roomType=" + roomType + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
