package de.christian2003.psychiatric.model.rooms;

import java.util.UUID;


public class RoomData {

    private final UUID roomId;

    private final String displayName;


    public RoomData(UUID roomId, String displayName) throws NullPointerException, IllegalArgumentException {
        if (roomId == null || displayName == null) {
            throw new NullPointerException();
        }
        if (displayName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.roomId = roomId;
        this.displayName = displayName;
    }


    public UUID getRoomId() {
        return roomId;
    }

    public String getDisplayName() {
        return displayName;
    }


    @Override
    public int hashCode() {
        return roomId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoomData roomData) {
            return roomData.getRoomId().equals(roomId);
        }
        return false;
    }

}
