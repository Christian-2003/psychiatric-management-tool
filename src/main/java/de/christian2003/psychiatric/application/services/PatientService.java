package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.application.repositories.PatientRepository;
import de.christian2003.psychiatric.domain.people.Patient;
import java.util.List;
import java.util.UUID;


public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) throws NullPointerException {
        if (patientRepository == null) {
            throw new NullPointerException();
        }
        this.patientRepository = patientRepository;
    }


    public Patient getPatientById(UUID id) throws NullPointerException {
        return patientRepository.getPatientById(id);
    }


    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }


    public void createPatient(Patient patient) throws NullPointerException {
        patientRepository.insertPatient(patient);
    }


    public void editPatient(Patient patient) throws NullPointerException {
        patientRepository.insertPatient(patient);
    }


    public void deletePatient(Patient patient) throws NullPointerException {
        patientRepository.deletePatient(patient);
    }

}
