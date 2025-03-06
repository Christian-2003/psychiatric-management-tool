package de.christian2003.psychiatric.model.people;

import java.util.UUID;

public class Patient {

    private final UUID patientId;

    private UUID assignedNurse;

    private final PersonalData personalData;


    public Patient(UUID patientId, PersonalData personalData) {
        if (patientId == null || personalData == null) {
            throw new NullPointerException();
        }
        this.patientId = patientId;
        this.personalData = personalData;
    }


    public UUID getPatientId() {
        return patientId;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public UUID getAssignedNurse() {
        return assignedNurse;
    }

    public void assignNurse(UUID assignedNurse) throws NullPointerException {
        if (assignedNurse == null) {
            throw new NullPointerException();
        }
        this.assignedNurse = assignedNurse;
    }


    @Override
    public int hashCode() {
        return patientId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient patient) {
            return patient.getPatientId().equals(patientId);
        }
        return false;
    }

}
