package model;

public class Room implements IRoom {

    public String roomNumber;
    public Double roomPrice;
    public RoomType roomType;
    public Boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType roomType, Boolean isFree) {
        super();
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", roomPrice=" + roomPrice + ", roomType=" + roomType + ", isFree="
                + isFree + "]";
    }
}
