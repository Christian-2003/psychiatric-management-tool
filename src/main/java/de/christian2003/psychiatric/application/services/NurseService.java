package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.application.repositories.NurseRepository;
import de.christian2003.psychiatric.domain.people.Nurse;
import java.util.List;
import java.util.UUID;


public class NurseService {

    private final NurseRepository nurseRepository;


    public NurseService(NurseRepository nurseRepository) throws NullPointerException {
        if (nurseRepository == null) {
            throw new NullPointerException();
        }
        this.nurseRepository = nurseRepository;
    }


    public Nurse getNurseById(UUID id) throws NullPointerException {
        return nurseRepository.getNurseById(id);
    }


    public List<Nurse> getAllNurses() {
        return nurseRepository.getAllNurses();
    }


    public void createNurse(Nurse nurse) throws NullPointerException {
        nurseRepository.insertNurse(nurse);
    }


    public void editNurse(Nurse nurse) throws NullPointerException {
        nurseRepository.insertNurse(nurse);
    }


    public void deleteNurse(Nurse nurse) throws NullPointerException {
        nurseRepository.deleteNurse(nurse);
    }

}
