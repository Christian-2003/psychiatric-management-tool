package de.christian2003.psychiatric.application.repositories;
import de.christian2003.psychiatric.domain.rooms.Office;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface OfficeRepository {

    Office getOfficeById(UUID id);

    List<Office> getAllOffices();

    void addOffice(Office office) throws NullPointerException;

    void deleteOffice(Office office) throws NullPointerException;

}
