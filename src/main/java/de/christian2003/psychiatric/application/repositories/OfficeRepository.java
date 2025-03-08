package de.christian2003.psychiatric.application.repositories;
import de.christian2003.psychiatric.domain.rooms.Office;

import java.util.List;
import java.util.UUID;


public interface OfficeRepository {

    Office getOfficeById(UUID id) throws NullPointerException;

    List<Office> getAllOffices();

    void insertOffice(Office office) throws NullPointerException;

    void deleteOffice(Office office) throws NullPointerException;

}
