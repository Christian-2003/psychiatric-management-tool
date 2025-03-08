package de.christian2003.psychiatric.application.repositories;

import de.christian2003.psychiatric.domain.people.Patient;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface PatientRepository {

    Patient getPatientById(UUID id);

    List<Patient> getAllPatients();

    void addPatient(Patient patient) throws NullPointerException;

    void deletePatient(Patient patient) throws NullPointerException;

}
