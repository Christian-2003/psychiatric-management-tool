package de.christian2003.psychiatric.domain.services;

import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import java.util.List;


public class PatientMover {

    private final CrisisInterventionAreaRepository crisisInterventionAreaRepository;


    public PatientMover(CrisisInterventionAreaRepository crisisInterventionAreaRepository) throws NullPointerException {
        if (crisisInterventionAreaRepository == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaRepository = crisisInterventionAreaRepository;
    }


    public void movePatientTo(Patient patient, CrisisInterventionArea crisisInterventionArea) throws NullPointerException, MoveException {
        if (patient == null || crisisInterventionArea == null) {
            throw new NullPointerException();
        }

        //Test whether crisis intervention area is free:
        if (crisisInterventionArea.hasAssignedPatient()) {
            if (crisisInterventionArea.getAssignedPatient().equals(patient.getPatientId())) {
                throw new MoveException("Patient \"" + patient.getPatientId() + "\" is already assigned to crisis intervention area \"" + crisisInterventionArea.getRoomId() + "\".");
            }
            else {
                throw new MoveException("Cannot move patient, since crisis intervention area with ID \"" + crisisInterventionArea.getRoomId() + "\" already has assigned patient.");
            }
        }

        //Remove patient from all current crisis intervention areas:
        List<CrisisInterventionArea> crisisInterventionAreas = crisisInterventionAreaRepository.getAllCrisisInterventionAreas();
        for (CrisisInterventionArea cia : crisisInterventionAreas) {
            if (cia.hasAssignedPatient() && cia.getAssignedPatient().equals(patient.getPatientId())) {
                cia.removeAssignedPatient();
            }
        }

        //Assign patient to crisis intervention area:
        crisisInterventionArea.assignPatient(patient.getPatientId());
        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
    }

}
