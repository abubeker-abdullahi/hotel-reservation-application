package model;

/**
 * Represents a free room in the hotel that is free of charge. This class extends the {@link Room} class.
 */
public class FreeRoom extends Room {

    /**
     * Constructs a FreeRoom object with the specified room number, room type, and free status.
     * The room price is always set to 0.0 for a free room.
     *
     * @param roomNumber the room number
     * @param roomPrice  the price of the room (ignored, always set to 0.0 for free rooms)
     * @param roomType   the type of the room (SINGLE or DOUBLE)
     * @param isFree     indicates if the room is free (true)
     */
    public FreeRoom(String roomNumber, Double roomPrice, RoomType roomType, Boolean isFree) {
        super(roomNumber, 0.0, roomType, isFree);
    }

    /**
     * Returns a string representation of the FreeRoom object.
     *
     * @return a string representation of the FreeRoom object
     */
    @Override
    public String toString() {
        return "FreeRoom {roomNumber=" + getRoomNumber() + ", roomPrice=$" + getRoomPrice() + ", roomType=" + getRoomType() + ", isFree=" + isFree() + "}";
    }
}
