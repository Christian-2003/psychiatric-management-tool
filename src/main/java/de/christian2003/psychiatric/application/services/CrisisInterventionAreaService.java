package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.domain.rooms.RoomData;
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


    public List<CrisisInterventionArea> getAllCrisisInterventionAreas() {
        return crisisInterventionAreaRepository.getAllCrisisInterventionAreas();
    }


    public void createCrisisInterventionArea(String displayName) throws NullPointerException {
        if (displayName == null) {
            throw new NullPointerException();
        }
        RoomData roomData = new RoomData(displayName);
        CrisisInterventionArea crisisInterventionArea = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(null)
                .build();
        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
    }


    public void editCrisisInterventionArea(UUID id, String displayName) throws NullPointerException, ServiceException {
        if (id == null) {
            throw new NullPointerException();
        }

        CrisisInterventionArea crisisInterventionArea = crisisInterventionAreaRepository.getCrisisInterventionAreaById(id);
        if (crisisInterventionArea == null) {
            throw new ServiceException("Cannot edit crisis intervention area, since no crisis intervention area with ID \"" + id + "\" exists.");
        }

        if (displayName == null) {
            displayName = crisisInterventionArea.getRoomData().getDisplayName();
        }

        RoomData roomData = new RoomData(displayName);
        crisisInterventionArea.updateRoomData(roomData);

        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
    }


    public void deleteCrisisInterventionArea(UUID id) throws NullPointerException, ServiceException {
        if (id == null) {
            throw new NullPointerException();
        }

        CrisisInterventionArea crisisInterventionArea = crisisInterventionAreaRepository.getCrisisInterventionAreaById(id);
        if (crisisInterventionArea == null) {
            throw new ServiceException("Cannot delete crisis intervention area, since no crisis intervention area with ID \"" + id + "\" exists.");
        }
        if (crisisInterventionArea.hasAssignedPatient()) {
            throw new ServiceException("Cannot delete crisis intervention area \"" + id + "\", since it has assigned patient.");
        }

        crisisInterventionAreaRepository.deleteCrisisInterventionArea(crisisInterventionArea);
    }

}
