package de.christian2003.psychiatric.database.file;

import de.christian2003.psychiatric.database.OfficeRepository;
import de.christian2003.psychiatric.model.rooms.Office;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class FileOfficeRepository implements OfficeRepository {

    private final FileSerializer<Office> serializer;


    public FileOfficeRepository() {
        serializer = new FileSerializer<>("offices.json");
    }


    @Override
    public Office getOfficeById(UUID id) throws IOException {
        List<Office> offices = serializer.loadAll();
        for (Office office : offices) {
            if (office.getRoomData().getRoomId().equals(id)) {
                return office;
            }
        }
        return null;
    }

    @Override
    public List<Office> getAllOffices() throws IOException {
        return serializer.loadAll();
    }

    @Override
    public void addOffice(Office office) throws NullPointerException, IOException {
        List<Office> offices = serializer.loadAll();
        int index = offices.indexOf(office);
        if (index != -1) {
            offices.set(index, office);
        }
        else {
            offices.add(office);
        }
        serializer.saveAll(offices);
    }

    @Override
    public void deleteOffice(Office office) throws NullPointerException, IOException {
        List<Office> offices = serializer.loadAll();
        offices.remove(office);
        serializer.saveAll(offices);
    }

}
