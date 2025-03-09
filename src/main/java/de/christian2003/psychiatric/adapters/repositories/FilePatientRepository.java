package de.christian2003.psychiatric.adapters.repositories;

import de.christian2003.psychiatric.application.repositories.PatientRepository;
import de.christian2003.psychiatric.application.repositories.SavableRepository;
import de.christian2003.psychiatric.plugins.gson.FileSerializer;
import de.christian2003.psychiatric.domain.people.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class FilePatientRepository implements PatientRepository, SavableRepository {

    private final FileSerializer<Patient> serializer;

    private final List<Patient> patients;


    public FilePatientRepository() {
        serializer = new FileSerializer<>("patients.json", Patient.class);
        patients = new ArrayList<>();
    }


    @Override
    public Patient getPatientById(UUID id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException();
        }
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        return Collections.unmodifiableList(patients);
    }

    @Override
    public void insertPatient(Patient patient) throws NullPointerException {
        if (patient == null) {
            throw new NullPointerException();
        }
        int index = patients.indexOf(patient);
        if (index != -1) {
            patients.set(index, patient);
        }
        else {
            patients.add(patient);
        }
    }

    @Override
    public void deletePatient(Patient patient) throws NullPointerException {
        if (patient == null) {
            throw new NullPointerException();
        }
        patients.remove(patient);
    }

    @Override
    public void saveData() throws IOException {
        serializer.saveAll(patients);
    }

    @Override
    public void loadData() throws IOException {
        patients.clear();
        patients.addAll(serializer.loadAll());
    }

}
