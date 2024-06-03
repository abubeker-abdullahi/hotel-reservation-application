package model;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, Double roomPrice, RoomType roomType, Boolean isFree) {
        super(roomNumber, 0.0, roomType, isFree);
    }

    @Override
    public String toString() {
        return "FreeRoom [roomNumber=" + getRoomNumber() + ", roomType=" + getRoomType() + ", isFree=" + isFree() + "]";
    }
}
