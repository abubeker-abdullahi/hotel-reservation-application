package model;

import model.Room.RoomType;

/**
 * The IRoom interface represents a room in a hotel. It defines methods to get the room's number, price, type, and its free status.
 */
public interface IRoom {

    /**
     * Gets the room number.
     * 
     * @return the room number
     */
    public String getRoomNumber();

    /**
     * Gets the price of the room.
     * 
     * @return the room price
     */
    public Double getRoomPrice();

    /**
     * Gets the type of the room.
     * 
     * @return the room type
     */
    public RoomType getRoomType();

    /**
     * Checks if the room is free.
     * 
     * @return true if the room is free, false otherwise
     */
    public boolean isFree();
}
