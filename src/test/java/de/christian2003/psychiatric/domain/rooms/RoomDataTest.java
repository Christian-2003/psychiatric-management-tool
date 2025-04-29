package de.christian2003.psychiatric.domain.rooms;

import org.junit.Assert;
import org.junit.Test;


public class RoomDataTest {

    @Test
    public void testCreate() {
        RoomData roomData = new RoomData("A265");

        Assert.assertEquals("A265", roomData.getDisplayName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithEmptyDisplayName() {
        new RoomData("");
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutDisplayName() {
        new RoomData(null);
    }


    @Test
    public void testEquals() {
        RoomData roomData1 = new RoomData("A265");
        RoomData roomData2 = new RoomData("A265");

        Assert.assertEquals(roomData1, roomData2);
    }


    @Test
    public void testNotEquals() {
        RoomData roomData1 = new RoomData("A265");
        RoomData roomData2 = new RoomData("F406");

        Assert.assertNotEquals(roomData1, roomData2);
    }


    @Test
    public void testHashCode() {
        RoomData roomData1 = new RoomData("A265");
        RoomData roomData2 = new RoomData("A265");

        Assert.assertEquals(roomData1.hashCode(), roomData2.hashCode());
    }


    @Test
    public void testNotHashCode() {
        RoomData roomData1 = new RoomData("A265");
        RoomData roomData2 = new RoomData("F406");

        Assert.assertNotEquals(roomData1.hashCode(), roomData2.hashCode());
    }

}
