package de.christian2003.psychiatric.domain.rooms;

import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.UUID;


public class CrisisInterventionAreaTest {

    @Test
    public void testCreate() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());
        Patient patient = new Patient(UUID.randomUUID(), personalData);
        RoomData roomData = new RoomData("A265");

        CrisisInterventionArea crisisInterventionArea = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(patient.getPatientId())
                .build();

        Assert.assertEquals(roomData, crisisInterventionArea.getRoomData());
        Assert.assertEquals(patient.getPatientId(), crisisInterventionArea.getAssignedPatient());
        Assert.assertTrue(crisisInterventionArea.hasAssignedPatient());
    }


    @Test
    public void testCreateWithoutPatient() {
        RoomData roomData = new RoomData("A265");

        CrisisInterventionArea crisisInterventionArea = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData).build();

        Assert.assertEquals(roomData, crisisInterventionArea.getRoomData());
        Assert.assertFalse(crisisInterventionArea.hasAssignedPatient());
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutId() {
        RoomData roomData = new RoomData("A265");

        new CrisisInterventionArea.Builder(null, roomData).build();
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutRoomData() {
        new CrisisInterventionArea.Builder(UUID.randomUUID(), null).build();
    }


    @Test
    public void testAssignPatient() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());
        Patient patient1 = new Patient(UUID.randomUUID(), personalData);
        RoomData roomData = new RoomData("A265");

        CrisisInterventionArea crisisInterventionArea = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(patient1.getPatientId())
                .build();

        Assert.assertEquals(patient1.getPatientId(), crisisInterventionArea.getAssignedPatient());

        Patient patient2 = new Patient(UUID.randomUUID(), personalData);
        crisisInterventionArea.assignPatient(patient2.getPatientId());

        Assert.assertEquals(patient2.getPatientId(), crisisInterventionArea.getAssignedPatient());
    }


    @Test
    public void testRemovePatient() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());
        Patient patient1 = new Patient(UUID.randomUUID(), personalData);
        RoomData roomData = new RoomData("A265");

        CrisisInterventionArea crisisInterventionArea = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData)
                .assignPatient(patient1.getPatientId())
                .build();

        Assert.assertTrue(crisisInterventionArea.hasAssignedPatient());

        crisisInterventionArea.removeAssignedPatient();

        Assert.assertFalse(crisisInterventionArea.hasAssignedPatient());
    }


    @Test
    public void testEquals() {
        RoomData roomData = new RoomData("A265");
        UUID uuid = UUID.randomUUID();

        CrisisInterventionArea crisisInterventionArea1 = new CrisisInterventionArea.Builder(uuid, roomData).build();
        CrisisInterventionArea crisisInterventionArea2 = new CrisisInterventionArea.Builder(uuid, roomData).build();

        Assert.assertEquals(crisisInterventionArea1, crisisInterventionArea2);
    }


    @Test
    public void testNotEquals() {
        RoomData roomData = new RoomData("A265");

        CrisisInterventionArea crisisInterventionArea1 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData).build();
        CrisisInterventionArea crisisInterventionArea2 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData).build();

        Assert.assertNotEquals(crisisInterventionArea1, crisisInterventionArea2);
    }


    @Test
    public void testHashCode() {
        RoomData roomData = new RoomData("A265");
        UUID uuid = UUID.randomUUID();

        CrisisInterventionArea crisisInterventionArea1 = new CrisisInterventionArea.Builder(uuid, roomData).build();
        CrisisInterventionArea crisisInterventionArea2 = new CrisisInterventionArea.Builder(uuid, roomData).build();

        Assert.assertEquals(crisisInterventionArea1.hashCode(), crisisInterventionArea2.hashCode());
    }


    @Test
    public void testNotHashCode() {
        RoomData roomData = new RoomData("A265");

        CrisisInterventionArea crisisInterventionArea1 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData).build();
        CrisisInterventionArea crisisInterventionArea2 = new CrisisInterventionArea.Builder(UUID.randomUUID(), roomData).build();

        Assert.assertNotEquals(crisisInterventionArea1.hashCode(), crisisInterventionArea2.hashCode());
    }

}
