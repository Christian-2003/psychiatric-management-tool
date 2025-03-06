package de.christian2003.psychiatric.model.people;

import java.util.Objects;
import java.util.UUID;


public class EmployeeData {

    private UUID roomId;

    private int workHoursPerWeek;


    public EmployeeData(UUID roomId, int workHoursPerWeek) throws NullPointerException, IllegalArgumentException {
        assignToRoom(roomId);
        setWorkHoursPerWeek(workHoursPerWeek);
    }


    public UUID getRoomId() {
        return roomId;
    }

    public void assignToRoom(UUID roomId) throws NullPointerException {
        if (roomId == null) {
            throw new NullPointerException();
        }
        this.roomId = roomId;
    }

    public int getWorkHoursPerWeek() {
        return workHoursPerWeek;
    }

    public void setWorkHoursPerWeek(int workHoursPerWeek) throws IllegalArgumentException {
        if (workHoursPerWeek < 0 || workHoursPerWeek > 50) {
            throw new IllegalArgumentException();
        }
        this.workHoursPerWeek = workHoursPerWeek;
    }


    @Override
    public int hashCode() {
        return Objects.hash(roomId, workHoursPerWeek);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EmployeeData employeeData) {
            return employeeData.getRoomId().equals(roomId)
                    && employeeData.getWorkHoursPerWeek() == workHoursPerWeek;
        }
        return false;
    }

}
