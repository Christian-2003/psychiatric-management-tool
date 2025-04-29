package de.christian2003.psychiatric.domain.services;

import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;
import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.domain.rooms.RoomData;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PatientMoverTest {

    /**
     * Class implements a mock for the repository used within the {@link PatientMover}.
     */
    private static class TestCrisisInterventionAreaRepository implements CrisisInterventionAreaRepository {

        private final List<CrisisInterventionArea> crisisInterventionAreas;


        public TestCrisisInterventionAreaRepository() {
            crisisInterventionAreas = new ArrayList<>();
        }


        @Override
        public CrisisInterventionArea getCrisisInterventionAreaById(UUID id) throws NullPointerException {
            for (CrisisInterventionArea cia : crisisInterventionAreas) {
                if (cia.getRoomId().equals(id)) {
                    return cia;
                }
            }
            return null;
        }

        @Override
        public List<CrisisInterventionArea> getAllCrisisInterventionAreas() {
            return crisisInterventionAreas;
        }

        @Override
        public void insertCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
            crisisInterventionAreas.add(crisisInterventionArea);
        }

        @Override
        public void deleteCrisisInterventionArea(CrisisInterventionArea crisisInterventionArea) throws NullPointerException {
            crisisInterventionAreas.remove(crisisInterventionArea);
        }
    }


    @Test
    public void testCreate() {
        CrisisInterventionAreaRepository repository = new TestCrisisInterventionAreaRepository();

        new PatientMover(repository);
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutRepository() {
        new PatientMover(null);
    }


    @Test
    public void testMove() throws MoveException {
        PersonalData personalData = new PersonalData("john", "doe", LocalDate.now());
        Patient patient = new Patient(UUID.randomUUID(), personalData);

        RoomData roomData = new RoomData("A265");
        CrisisInterventionArea crisisInterventionArea1 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(patient.getPatientId())
                .build();
        CrisisInterventionArea crisisInterventionArea2 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .build();

        CrisisInterventionAreaRepository repository = new TestCrisisInterventionAreaRepository();
        repository.insertCrisisInterventionArea(crisisInterventionArea1);
        repository.insertCrisisInterventionArea(crisisInterventionArea2);

        Assert.assertNotNull(repository.getCrisisInterventionAreaById(crisisInterventionArea1.getRoomId()));
        Assert.assertNotNull(repository.getCrisisInterventionAreaById(crisisInterventionArea2.getRoomId()));

        PatientMover patientMover = new PatientMover(repository);

        Assert.assertEquals(patient.getPatientId(), crisisInterventionArea1.getAssignedPatient());
        Assert.assertFalse(crisisInterventionArea2.hasAssignedPatient());

        patientMover.movePatientTo(patient, crisisInterventionArea2);

        Assert.assertEquals(patient.getPatientId(), crisisInterventionArea2.getAssignedPatient());
        Assert.assertFalse(crisisInterventionArea1.hasAssignedPatient());
    }


    @Test(expected = MoveException.class)
    public void testMoveToAssignedRoom() throws MoveException {
        PersonalData personalData = new PersonalData("john", "doe", LocalDate.now());
        Patient patient1 = new Patient(UUID.randomUUID(), personalData);
        Patient patient2 = new Patient(UUID.randomUUID(), personalData);

        RoomData roomData = new RoomData("A265");
        CrisisInterventionArea crisisInterventionArea1 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(patient1.getPatientId())
                .build();
        CrisisInterventionArea crisisInterventionArea2 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(patient2.getPatientId())
                .build();

        CrisisInterventionAreaRepository repository = new TestCrisisInterventionAreaRepository();
        repository.insertCrisisInterventionArea(crisisInterventionArea1);
        repository.insertCrisisInterventionArea(crisisInterventionArea2);

        Assert.assertNotNull(repository.getCrisisInterventionAreaById(crisisInterventionArea1.getRoomId()));
        Assert.assertNotNull(repository.getCrisisInterventionAreaById(crisisInterventionArea2.getRoomId()));

        PatientMover patientMover = new PatientMover(repository);

        Assert.assertEquals(patient1.getPatientId(), crisisInterventionArea1.getAssignedPatient());
        Assert.assertEquals(patient2.getPatientId(), crisisInterventionArea2.getAssignedPatient());

        patientMover.movePatientTo(patient1, crisisInterventionArea2);
    }

}
