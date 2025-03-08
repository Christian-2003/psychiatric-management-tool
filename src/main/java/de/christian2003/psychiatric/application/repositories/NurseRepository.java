package de.christian2003.psychiatric.application.repositories;

import de.christian2003.psychiatric.domain.people.Nurse;

import java.util.List;
import java.util.UUID;


public interface NurseRepository {

    Nurse getNurseById(UUID id) throws NullPointerException;

    List<Nurse> getAllNurses();

    void insertNurse(Nurse nurse) throws NullPointerException;

    void deleteNurse(Nurse nurse) throws NullPointerException;

}
