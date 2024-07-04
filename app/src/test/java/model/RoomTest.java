package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Room.RoomType;

/**
 * Test class for the {@link model.Room} and {@link model.FreeRoom} classes.
 */
public class RoomTest {
    private FreeRoom freeroom;
    private Room room;

    /**
     * Sets up the test data before each test.
     */
    @BeforeEach
    public void setup() {
        freeroom = new FreeRoom("100", 0.0, RoomType.SINGLE, true);
        room = new Room("100", 120.5, RoomType.SINGLE, true);
    }

    /**
     * Tests the setter and getter methods of the {@link model.Room} class.
     */
    @Test
    public void testRoomSettersAndGetters() {
        assertEquals("100", freeroom.getRoomNumber());
        assertEquals(0.0, freeroom.getRoomPrice());
        assertEquals(RoomType.SINGLE, freeroom.getRoomType());
        assertEquals(true, freeroom.isFree());

        room.setRoomNumber("200");
        room.setRoomPrice(1.0);
        room.setRoomType(RoomType.DOUBLE);
        room.setIsFree(false);

        assertEquals("200", room.getRoomNumber());
        assertEquals(1.0, room.getRoomPrice());
        assertEquals(RoomType.DOUBLE, room.getRoomType());
        assertEquals(false, room.isFree());
    }

    /**
     * Tests that an empty room number throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testEmptyRoomNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Room("  ", 150.0, RoomType.SINGLE, true);
        });
    }

    /**
     * Tests that a null room number throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullRoomNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, 150.0, RoomType.SINGLE, true);
        });
    }

    /**
     * Tests that a null room price throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullRoomPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Room("100", null, RoomType.SINGLE, true);
        });
    }

    /**
     * Tests that a null room type throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullRoomType() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Room("100", 150.0, null, true);
        });
    }

    /**
     * Tests that a null free status throws an {@link IllegalArgumentException}.
     */
    @Test
    public void testNullisFree() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Room("100", 150.0, RoomType.SINGLE, null);
        });
    }

    /**
     * Tests the {@link model.FreeRoom#toString()} method.
     */
    @Test
    public void testFreeRoomToString() {
        assertEquals("FreeRoom {roomNumber=100, roomPrice=$0.0, roomType=SINGLE, isFree=true}", freeroom.toString());
    }

    /**
     * Tests the {@link model.Room#toString()} method.
     */
    @Test
    public void testRoomToString() {
        assertEquals("Room {roomNumber=100, roomPrice=$120.5, roomType=SINGLE}", room.toString());
    }
}
