package de.christian2003.psychiatric.model.rooms;

public class Office {

    private final RoomData roomData;


    public Office(RoomData roomData) throws NullPointerException {
        if (roomData == null) {
            throw new NullPointerException();
        }
        this.roomData = roomData;
    }


    public RoomData getRoomData() {
        return roomData;
    }


    @Override
    public int hashCode() {
        return roomData.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Office office) {
            return office.getRoomData().equals(roomData);
        }
        return false;
    }

}
