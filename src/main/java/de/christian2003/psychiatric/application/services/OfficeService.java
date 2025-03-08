package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.application.repositories.OfficeRepository;
import de.christian2003.psychiatric.domain.rooms.Office;
import java.util.List;
import java.util.UUID;

public class OfficeService {

    private final OfficeRepository officeRepository;


    public OfficeService(OfficeRepository officeRepository) throws NullPointerException {
        if (officeRepository == null) {
            throw new NullPointerException();
        }
        this.officeRepository = officeRepository;
    }


    public Office getOfficeById(UUID id) throws NullPointerException {
        return officeRepository.getOfficeById(id);
    }


    public List<Office> getAllOffices() {
        return officeRepository.getAllOffices();
    }


    public void createOffice(Office office) throws NullPointerException {
        officeRepository.insertOffice(office);
    }


    public void editOffice(Office office) throws NullPointerException {
        officeRepository.insertOffice(office);
    }


    public void deleteOffice(Office office) throws NullPointerException {
        officeRepository.deleteOffice(office);
    }

}
