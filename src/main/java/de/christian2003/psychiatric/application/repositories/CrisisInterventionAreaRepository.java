package de.christian2003.psychiatric.application.repositories;

import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import java.util.List;
import java.util.UUID;


public interface CrisisInterventionAreaRepository {

    CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws NullPointerException;

    List<CrisisInterventionArea> getAllCrisisInterventionAreas();

    void insertCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException;

    void deleteCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException;

}
