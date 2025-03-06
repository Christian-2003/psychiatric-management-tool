package de.christian2003.psychiatric.model.people;

import java.util.UUID;


public class Nurse {

    private final UUID employeeId;

    private final PersonalData personalData;

    private final EmployeeData employeeData;


    public Nurse(UUID employeeId, PersonalData personalData, EmployeeData employeeData) throws NullPointerException {
        if (employeeId == null || personalData == null || employeeData == null) {
            throw new NullPointerException();
        }
        this.employeeId = employeeId;
        this.personalData = personalData;
        this.employeeData = employeeData;
    }


    public UUID getEmployeeId() {
        return employeeId;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public EmployeeData getEmployeeData() {
        return employeeData;
    }


    @Override
    public int hashCode() {
        return employeeId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Nurse nurse) {
            return nurse.getEmployeeId().equals(employeeId);
        }
        return false;
    }

}
