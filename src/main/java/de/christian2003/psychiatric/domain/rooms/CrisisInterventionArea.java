package de.christian2003.psychiatric.domain.rooms;

import java.util.UUID;


public class CrisisInterventionArea {

    private RoomData roomData;

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

    public void updateRoomData(RoomData roomData) throws NullPointerException {
        if (roomData == null) {
            throw new NullPointerException();
        }
        this.roomData = roomData;
    }

    public UUID getAssignedPatient() {
        return  assignedPatient;
    }

    public void assignPatient(UUID patient) throws NullPointerException {
        if (patient == null) {
            throw new NullPointerException();
        }
        this.assignedPatient = patient;
    }

    public void removeAssignedPatient() {
        this.assignedPatient = null;
    }

    public boolean hasAssignedPatient() {
        return assignedPatient != null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(roomData);
        builder.append(" (Assigned Patient: ");
        if (assignedPatient == null) {
            builder.append("None");
        }
        else {
            builder.append(assignedPatient);
        }
        builder.append(")");

        return builder.toString();
    }

}
