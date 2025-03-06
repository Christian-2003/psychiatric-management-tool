package de.christian2003.psychiatric.database.file;

import de.christian2003.psychiatric.database.NurseRepository;
import de.christian2003.psychiatric.model.people.Nurse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class FileNurseRepository implements NurseRepository {

    private final FileSerializer<Nurse> serializer;


    public FileNurseRepository() {
        serializer = new FileSerializer<>("nurses.json");
    }


    @Override
    public Nurse getNurseById(UUID id) throws IOException {
        List<Nurse> nurses = serializer.loadAll();
        for (Nurse nurse : nurses) {
            if (nurse.getEmployeeId().equals(id)) {
                return nurse;
            }
        }
        return null;
    }

    @Override
    public List<Nurse> getAllNurses() throws IOException {
        return serializer.loadAll();
    }

    @Override
    public void addNurse(Nurse nurse) throws NullPointerException, IOException {
        List<Nurse> nurses = serializer.loadAll();
        int index = nurses.indexOf(nurse);
        if (index != -1) {
            nurses.set(index, nurse);
        }
        else {
            nurses.add(nurse);
        }
        serializer.saveAll(nurses);
    }

    @Override
    public void deleteNurse(Nurse nurse) throws NullPointerException, IOException {
        List<Nurse> nurses = serializer.loadAll();
        nurses.remove(nurse);
        serializer.saveAll(nurses);
    }

}
