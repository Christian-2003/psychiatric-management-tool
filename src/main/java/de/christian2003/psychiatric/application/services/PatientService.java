package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.application.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.application.repositories.PatientRepository;
import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class PatientService {

    private final PatientRepository patientRepository;

    private final CrisisInterventionAreaRepository crisisInterventionAreaRepository;


    public PatientService(PatientRepository patientRepository, CrisisInterventionAreaRepository crisisInterventionAreaRepository) throws NullPointerException {
        if (patientRepository == null || crisisInterventionAreaRepository == null) {
            throw new NullPointerException();
        }
        this.patientRepository = patientRepository;
        this.crisisInterventionAreaRepository = crisisInterventionAreaRepository;
    }


    public Patient getPatientById(UUID id) throws NullPointerException {
        return patientRepository.getPatientById(id);
    }


    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }


    public Patient createPatient(String firstname, String lastname, LocalDate birthday, UUID crisisInterventionArea) throws NullPointerException, ServiceException {
        if (firstname == null || lastname == null || birthday == null || crisisInterventionArea == null) {
            throw new NullPointerException();
        }

        PersonalData personalData = new PersonalData(firstname, lastname, birthday);
        Patient patient = new Patient(UUID.randomUUID(), personalData);

        //Assign patient to crisis intervention area:
        CrisisInterventionArea cia = crisisInterventionAreaRepository.getCrisisInterventionAreaById(crisisInterventionArea);
        if (cia == null) {
            throw new ServiceException("Cannot create patient, since no crisis intervention area with ID \"" + crisisInterventionArea + "\" exists.");
        }
        if (cia.hasAssignedPatient()) {
            throw new ServiceException("Cannot create patient, since crisis intervention area " + crisisInterventionArea + " already has assigned patient.");
        }
        cia.assignPatient(patient.getPatientId());

        patientRepository.insertPatient(patient);
        return patient;
    }


    public Patient editPatient(UUID id, String newFirstname, String newLastname, LocalDate newBirthday) throws NullPointerException, ServiceException {
        if (id == null) {
            throw new NullPointerException();
        }

        Patient patient = patientRepository.getPatientById(id);
        if (patient == null) {
            throw new ServiceException("Cannot edit patient, since no patient with ID \"" + id + "\" exists.");
        }

        if (newFirstname == null) {
            newFirstname = patient.getPersonalData().getFirstname();
        }
        if (newLastname == null) {
            newLastname = patient.getPersonalData().getLastname();
        }
        if (newBirthday == null) {
            newBirthday = patient.getPersonalData().getBirthday();
        }

        PersonalData personalData = new PersonalData(newFirstname, newLastname, newBirthday);
        patient.updatePersonalData(personalData);
        patientRepository.insertPatient(patient);

        return patient;
    }


    public Patient deletePatient(UUID patientId) throws NullPointerException, ServiceException {
        if (patientId == null) {
            throw new NullPointerException();
        }

        Patient patient = patientRepository.getPatientById(patientId);
        if (patient == null) {
            throw new ServiceException("Cannot delete patient, since no patient with ID \"" + patientId + "\" exists.");
        }
        patientRepository.deletePatient(patient);

        //Remove patient from assigned crisis intervention area:
        List<CrisisInterventionArea> crisisInterventionAreas = crisisInterventionAreaRepository.getAllCrisisInterventionAreas();
        for (CrisisInterventionArea crisisInterventionArea: crisisInterventionAreas) {
            if (crisisInterventionArea.getAssignedPatient().equals(patient.getPatientId())) {
                crisisInterventionArea.removeAssignedPatient();
            }
        }

        return patient;
    }


    public void movePatientToCrisisInterventionArea(UUID patientId, UUID crisisInterventionAreaId) throws NullPointerException, ServiceException {
        if (patientId == null || crisisInterventionAreaId == null) {
            throw new NullPointerException();
        }

        //Test whether patient exists:
        Patient patient = patientRepository.getPatientById(patientId);
        if (patient == null) {
            throw new ServiceException("Cannot move patient, since no patient with ID \"" + patientId + "\" exists.");
        }

        //Test whether crisis intervention area exists and is free:
        CrisisInterventionArea crisisInterventionArea = crisisInterventionAreaRepository.getCrisisInterventionAreaById(crisisInterventionAreaId);
        if (crisisInterventionArea == null) {
            throw new ServiceException("Cannot move patient, since no crisis intervention area with ID \"" + crisisInterventionAreaId + "\" exists.");
        }
        if (crisisInterventionArea.hasAssignedPatient()) {
            if (crisisInterventionArea.getAssignedPatient().equals(patientId)) {
                throw new ServiceException("Patient \"" + patientId + "\" is already assigned to crisis intervention area \"" + crisisInterventionAreaId + "\".");
            }
            else {
                throw new ServiceException("Cannot move patient, since crisis intervention area with ID \"" + crisisInterventionAreaId + "\" already has assigned patient.");
            }
        }

        //Remove patient from all current crisis intervention areas:
        List<CrisisInterventionArea> crisisInterventionAreas = crisisInterventionAreaRepository.getAllCrisisInterventionAreas();
        for (CrisisInterventionArea cia : crisisInterventionAreas) {
            if (cia.hasAssignedPatient() && cia.getAssignedPatient().equals(patientId)) {
                cia.removeAssignedPatient();
            }
        }

        //Assign patient to crisis intervention area:
        crisisInterventionArea.assignPatient(patientId);
        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
    }

}
