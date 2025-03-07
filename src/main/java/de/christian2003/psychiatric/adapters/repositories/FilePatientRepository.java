package de.christian2003.psychiatric.adapters.repositories;

import de.christian2003.psychiatric.application.repositories.PatientRepository;
import de.christian2003.psychiatric.plugins.FileSerializer;
import de.christian2003.psychiatric.domain.people.Patient;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class FilePatientRepository implements PatientRepository {

    private final FileSerializer<Patient> serializer;


    public FilePatientRepository() {
        serializer = new FileSerializer<>("patients.json");
    }


    @Override
    public Patient getPatientById(UUID id) throws IOException {
        List<Patient> patients = serializer.loadAll();
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() throws IOException {
        return serializer.loadAll();
    }

    @Override
    public void addPatient(Patient patient) throws NullPointerException, IOException {
        List<Patient> patients = serializer.loadAll();
        int index = patients.indexOf(patient);
        if (index != -1) {
            patients.set(index, patient);
        }
        else {
            patients.add(patient);
        }
        serializer.saveAll(patients);
    }

    @Override
    public void deletePatient(Patient patient) throws NullPointerException, IOException {
        List<Patient> patients = serializer.loadAll();
        patients.remove(patient);
        serializer.saveAll(patients);
    }

}
