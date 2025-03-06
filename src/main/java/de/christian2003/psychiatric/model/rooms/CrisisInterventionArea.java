package de.christian2003.psychiatric.model.rooms;

import java.util.UUID;

public class CrisisInterventionArea {

    private final RoomData roomData;

    private UUID assignedPatient;


    public CrisisInterventionArea(RoomData roomData, UUID assignedPatient) throws NullPointerException {
        if (roomData == null) {
            throw new NullPointerException();
        }
        this.roomData = roomData;
        assignPatient(assignedPatient);
    }


    public RoomData getRoomData() {
        return roomData;
    }

    public UUID getAssignedPatient() {
        return  assignedPatient;
    }

    public void assignPatient(UUID patient) {
        this.assignedPatient = patient;
    }

    public boolean hasAssignedPatient() {
        return assignedPatient != null;
    }

}
