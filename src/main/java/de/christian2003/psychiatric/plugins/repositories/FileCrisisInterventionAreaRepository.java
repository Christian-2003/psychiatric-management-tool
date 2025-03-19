package de.christian2003.psychiatric.plugins.repositories;

import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.repositories.SavableRepository;
import de.christian2003.psychiatric.plugins.gson.FileSerializer;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import java.io.IOException;
import java.util.*;


public class FileCrisisInterventionAreaRepository implements CrisisInterventionAreaRepository, SavableRepository {

    private final FileSerializer<CrisisInterventionArea> serializer;

    private final List<CrisisInterventionArea> crisisInterventionAreas;


    public FileCrisisInterventionAreaRepository() {
        serializer = new FileSerializer<>("crisisinterventionareas.json", CrisisInterventionArea.class);
        crisisInterventionAreas = new ArrayList<>();
    }


    @Override
    public CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException();
        }
        for (CrisisInterventionArea crisisInterventionArea : crisisInterventionAreas) {
            if (crisisInterventionArea.getRoomId().equals(id)) {
                return crisisInterventionArea;
            }
        }
        return null;
    }

    @Override
    public List<CrisisInterventionArea> getAllCrisisInterventionAreas() {
        return Collections.unmodifiableList(crisisInterventionAreas);
    }

    @Override
    public void insertCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
        if (crisisInterventionArea == null) {
            throw new NullPointerException();
        }
        int index = crisisInterventionAreas.indexOf(crisisInterventionArea);
        if (index != -1) {
            crisisInterventionAreas.set(index, crisisInterventionArea);
        }
        else {
            crisisInterventionAreas.add(crisisInterventionArea);
        }
    }

    @Override
    public void deleteCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
        if (crisisInterventionArea == null) {
            throw new NullPointerException();
        }
        crisisInterventionAreas.remove(crisisInterventionArea);
    }


    @Override
    public void saveData() throws IOException {
        serializer.saveAll(crisisInterventionAreas);
    }

    @Override
    public void loadData() throws IOException {
        crisisInterventionAreas.clear();
        crisisInterventionAreas.addAll(serializer.loadAll());
    }

}
