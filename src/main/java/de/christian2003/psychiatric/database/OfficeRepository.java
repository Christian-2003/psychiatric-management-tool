package de.christian2003.psychiatric.database;
import de.christian2003.psychiatric.model.rooms.Office;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface OfficeRepository {

    Office getOfficeById(UUID id) throws IOException;

    List<Office> getAllOffices() throws IOException;

    void addOffice(Office office) throws NullPointerException, IOException;

    void deleteOffice(Office office) throws NullPointerException, IOException;

}
