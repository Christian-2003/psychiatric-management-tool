package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.application.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.application.repositories.NurseRepository;
import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;

import java.util.List;
import java.util.UUID;

public class CrisisInterventionAreaService {

    private final CrisisInterventionAreaRepository crisisInterventionAreaRepository;


    public CrisisInterventionAreaService(CrisisInterventionAreaRepository crisisInterventionAreaRepository) throws NullPointerException {
        if (crisisInterventionAreaRepository == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaRepository = crisisInterventionAreaRepository;
    }


    public CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws NullPointerException {
        return crisisInterventionAreaRepository.getCrisisInterventionAreaById(id);
    }


    public List<CrisisInterventionArea> getAllCrisisInterventionAreas() {
        return crisisInterventionAreaRepository.getAllCrisisInterventionAreas();
    }


    public void createCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
    }


    public void editCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
    }


    public void deleteCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
        crisisInterventionAreaRepository.deleteCrisisInterventionArea(crisisInterventionArea);
    }

}
