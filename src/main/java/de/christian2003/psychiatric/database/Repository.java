package de.christian2003.psychiatric.database;

import de.christian2003.psychiatric.model.people.Nurse;
import de.christian2003.psychiatric.model.people.Patient;
import de.christian2003.psychiatric.model.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.model.rooms.Office;

import java.util.ArrayList;
import java.util.UUID;

public class Repository {

    private final ArrayList<Nurse> nurses;

    private final ArrayList<Patient> patients;

    private final ArrayList<Office> offices;

    private final ArrayList<CrisisInterventionArea> crisisInterventionAreas;


    public Repository() {
        nurses = new ArrayList<>();
        patients = new ArrayList<>();
        offices = new ArrayList<>();
        crisisInterventionAreas = new ArrayList<>();
    }


    public ArrayList<Nurse> getAllNurses() {
        return new ArrayList<>(nurses);
    }

    public ArrayList<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public ArrayList<Office> getAllOffices() {
        return new ArrayList<>(offices);
    }

    public ArrayList<CrisisInterventionArea> getAllCrisisInterventionAreas() {
        return new ArrayList<>(crisisInterventionAreas);
    }


    public Nurse getNurseById(UUID employeeId) throws NullPointerException {
        if (employeeId == null) {
            throw new NullPointerException();
        }
        for (Nurse nurse : nurses) {
            if (nurse.getEmployeeId().equals(employeeId)) {
                return nurse;
            }
        }
        return null;
    }

    public Patient getPatientByIdL(UUID patientId) throws NullPointerException {
        if (patientId == null) {
            throw new NullPointerException();
        }
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    public Office getOfficeById(UUID roomId) throws NullPointerException {
        if (roomId == null) {
            throw new NullPointerException();
        }
        for (Office office : offices) {
            if (office.getRoomData().getRoomId().equals(roomId)) {
                return office;
            }
        }
        return null;
    }

    public CrisisInterventionArea getCrisisInterventionAreaById(UUID roomId) throws NullPointerException {
        if (roomId == null) {
            throw new NullPointerException();
        }
        for (CrisisInterventionArea crisisInterventionArea : crisisInterventionAreas) {
            if (crisisInterventionArea.getRoomData().getRoomId().equals(roomId)) {
                return crisisInterventionArea;
            }
        }
        return null;
    }


    public void insertNurse(Nurse nurse) throws NullPointerException {
        if (nurse == null) {
            throw new NullPointerException();
        }
        int index = nurses.indexOf(nurse);
        if (index != -1) {
            nurses.set(index, nurse);
        }
        else {
            nurses.add(nurse);
        }
    }

}
