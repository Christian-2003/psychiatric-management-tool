package de.christian2003.psychiatric.adapters.repositories;

import de.christian2003.psychiatric.application.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.plugins.FileSerializer;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class FileCrisisInterventionAreaRepository implements CrisisInterventionAreaRepository {

    private final FileSerializer<CrisisInterventionArea> serializer;


    public FileCrisisInterventionAreaRepository() {
        serializer = new FileSerializer<>("crisisinterventionareas.json");
    }


    @Override
    public CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws IOException {
        List<CrisisInterventionArea> crisisInterventionAreas = serializer.loadAll();
        for (CrisisInterventionArea crisisInterventionArea : crisisInterventionAreas) {
            if (crisisInterventionArea.getRoomData().getRoomId().equals(id)) {
                return crisisInterventionArea;
            }
        }
        return null;
    }

    @Override
    public List<CrisisInterventionArea> getAllCrisisInterventionAreas() throws IOException {
        return serializer.loadAll();
    }

    @Override
    public void addCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException, IOException {
        List<CrisisInterventionArea> crisisInterventionAreas = serializer.loadAll();
        int index = crisisInterventionAreas.indexOf(crisisInterventionArea);
        if (index != -1) {
            crisisInterventionAreas.set(index, crisisInterventionArea);
        }
        else {
            crisisInterventionAreas.add(crisisInterventionArea);
        }
        serializer.saveAll(crisisInterventionAreas);
    }

    @Override
    public void deleteCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException, IOException {
        List<CrisisInterventionArea> crisisInterventionAreas = serializer.loadAll();
        crisisInterventionAreas.remove(crisisInterventionArea);
        serializer.saveAll(crisisInterventionAreas);
    }

}
