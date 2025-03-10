package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.application.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.application.repositories.NurseRepository;
import de.christian2003.psychiatric.domain.people.Patient;
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


    public CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws NullPointerException {
        return crisisInterventionAreaRepository.getCrisisInterventionAreaById(id);
    }


    public List<CrisisInterventionArea> getAllCrisisInterventionAreas() {
        return crisisInterventionAreaRepository.getAllCrisisInterventionAreas();
    }


    public CrisisInterventionArea createCrisisInterventionArea(String displayName) throws NullPointerException {
        if (displayName == null) {
            throw new NullPointerException();
        }
        RoomData roomData = new RoomData(displayName);
        CrisisInterventionArea crisisInterventionArea = new CrisisInterventionArea(UUID.randomUUID(), roomData, null);
        crisisInterventionAreaRepository.insertCrisisInterventionArea(crisisInterventionArea);
        return crisisInterventionArea;
    }


    public CrisisInterventionArea editCrisisInterventionArea(UUID id, String displayName) throws NullPointerException, ServiceException {
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

        return crisisInterventionArea;
    }


    public CrisisInterventionArea deleteCrisisInterventionArea(UUID id) throws NullPointerException, ServiceException {
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

        return crisisInterventionArea;
    }

}
