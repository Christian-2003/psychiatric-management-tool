package de.christian2003.psychiatric.database;

import de.christian2003.psychiatric.model.rooms.CrisisInterventionArea;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface CrisisInterventionAreaRepository {

    CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws IOException;

    List<CrisisInterventionArea> getAllCrisisInterventionAreas() throws IOException;

    void addCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException, IOException;

    void deleteCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException, IOException;

}
