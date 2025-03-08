package de.christian2003.psychiatric.application.repositories;

import de.christian2003.psychiatric.domain.people.Patient;
import java.util.List;
import java.util.UUID;


public interface PatientRepository {

    Patient getPatientById(UUID id) throws NullPointerException;

    List<Patient> getAllPatients();

    void insertPatient(Patient patient) throws NullPointerException;

    void deletePatient(Patient patient) throws NullPointerException;

}
