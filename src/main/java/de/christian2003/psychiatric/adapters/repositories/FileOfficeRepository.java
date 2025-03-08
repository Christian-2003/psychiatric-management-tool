package de.christian2003.psychiatric.adapters.repositories;

import de.christian2003.psychiatric.application.repositories.OfficeRepository;
import de.christian2003.psychiatric.application.repositories.SavableRepository;
import de.christian2003.psychiatric.plugins.FileSerializer;
import de.christian2003.psychiatric.domain.rooms.Office;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class FileOfficeRepository implements OfficeRepository, SavableRepository {

    private final FileSerializer<Office> serializer;

    private final List<Office> offices;


    public FileOfficeRepository() {
        serializer = new FileSerializer<>("offices.json");
        offices = new ArrayList<>();
    }


    @Override
    public Office getOfficeById(UUID id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException();
        }
        for (Office office : offices) {
            if (office.getRoomData().getRoomId().equals(id)) {
                return office;
            }
        }
        return null;
    }

    @Override
    public List<Office> getAllOffices() {
        return Collections.unmodifiableList(offices);
    }

    @Override
    public void addOffice(Office office) throws NullPointerException {
        if (office == null) {
            throw new NullPointerException();
        }
        int index = offices.indexOf(office);
        if (index != -1) {
            offices.set(index, office);
        }
        else {
            offices.add(office);
        }
    }

    @Override
    public void deleteOffice(Office office) throws NullPointerException {
        if (office == null) {
            throw new NullPointerException();
        }
        offices.remove(office);
    }

    @Override
    public void saveData() throws IOException {
        serializer.saveAll(offices);
    }

    @Override
    public void loadData() throws IOException {
        offices.clear();
        offices.addAll(serializer.loadAll());
    }

}
