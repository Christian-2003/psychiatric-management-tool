package de.christian2003.psychiatric.database;

import de.christian2003.psychiatric.model.people.Patient;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface PatientRepository {

    Patient getPatientById(UUID id) throws IOException;

    List<Patient> getAllPatients() throws IOException;

    void addPatient(Patient patient) throws NullPointerException, IOException;

    void deletePatient(Patient patient) throws NullPointerException, IOException;

}
