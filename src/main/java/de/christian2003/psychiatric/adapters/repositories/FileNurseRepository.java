package de.christian2003.psychiatric.adapters.repositories;

import de.christian2003.psychiatric.application.repositories.NurseRepository;
import de.christian2003.psychiatric.application.repositories.SavableRepository;
import de.christian2003.psychiatric.plugins.gson.FileSerializer;
import de.christian2003.psychiatric.domain.people.Nurse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class FileNurseRepository implements NurseRepository, SavableRepository {

    private final FileSerializer<Nurse> serializer;

    private final List<Nurse> nurses;


    public FileNurseRepository() {
        serializer = new FileSerializer<>("nurses.json", Nurse.class);
        nurses = new ArrayList<>();
    }


    @Override
    public Nurse getNurseById(UUID id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException();
        }
        for (Nurse nurse : nurses) {
            if (nurse.getEmployeeId().equals(id)) {
                return nurse;
            }
        }
        return null;
    }

    @Override
    public List<Nurse> getAllNurses() {
        return Collections.unmodifiableList(nurses);
    }

    @Override
    public void insertNurse(Nurse nurse) throws NullPointerException {
        if (nurse == null) {
            throw new NullPointerException();
        }
        int index = nurses.indexOf(nurse);
        if (index != -1) {
            nurses.set(index, nurse);
        }
        else {
            nurses.add(nurse);
        }
    }

    @Override
    public void deleteNurse(Nurse nurse) throws NullPointerException {
        if (nurse == null) {
            throw new NullPointerException();
        }
        nurses.remove(nurse);
    }


    @Override
    public void saveData() throws IOException {
        serializer.saveAll(nurses);
    }

    @Override
    public void loadData() throws IOException {
        nurses.clear();
        nurses.addAll(serializer.loadAll());
    }

}
