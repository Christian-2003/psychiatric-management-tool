package de.christian2003.psychiatric.domain.rooms;

import java.util.UUID;


public final class RoomData {

    private final String displayName;


    public RoomData(String displayName) throws NullPointerException, IllegalArgumentException {
        if (displayName == null) {
            throw new NullPointerException();
        }
        if (displayName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }


    @Override
    public int hashCode() {
        return displayName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoomData roomData) {
            return displayName.equals(roomData.getDisplayName());
        }
        return false;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
