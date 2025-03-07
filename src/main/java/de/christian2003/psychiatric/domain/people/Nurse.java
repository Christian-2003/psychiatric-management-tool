package de.christian2003.psychiatric.domain.people;

import java.util.UUID;


public class Nurse {

    private final UUID employeeId;

    private final PersonalData personalData;

    private UUID officeId;


    public Nurse(UUID employeeId, UUID officeId, PersonalData personalData) throws NullPointerException {
        if (employeeId == null || officeId == null || personalData == null) {
            throw new NullPointerException();
        }
        this.employeeId = employeeId;
        this.personalData = personalData;
        this.officeId = officeId;
    }


    public UUID getEmployeeId() {
        return employeeId;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public UUID getOfficeId() {
        return officeId;
    }

    public void assignToOffice(UUID officeId) throws NullPointerException {
        if (officeId == null) {
            throw new NullPointerException();
        }
        this.officeId = officeId;
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
