package de.christian2003.psychiatric.domain.people;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.UUID;


public class PersonTest {

    @Test
    public void testCreate() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());
        UUID uuid = UUID.randomUUID();

        Patient patient = new Patient(uuid, personalData);

        Assert.assertEquals(personalData, patient.getPersonalData());
        Assert.assertEquals(uuid, patient.getPatientId());
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutId() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());

        new Patient(null, personalData);
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutPersonalDate() {
        UUID uuid = UUID.randomUUID();

        new Patient(uuid, null);
    }


    @Test
    public void testUpdatePersonalData() {
        PersonalData personalData1 = new PersonalData("firstname", "lastname", LocalDate.now());
        UUID uuid = UUID.randomUUID();

        Patient patient = new Patient(uuid, personalData1);

        Assert.assertEquals(personalData1, patient.getPersonalData());
        Assert.assertEquals(uuid, patient.getPatientId());

        PersonalData personalData2 = new PersonalData("john", "doe", LocalDate.now());
        patient.updatePersonalData(personalData2);

        Assert.assertEquals(personalData2, patient.getPersonalData());
    }


    @Test
    public void testEquals() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());
        UUID uuid = UUID.randomUUID();

        Patient patient1 = new Patient(uuid, personalData);
        Patient patient2 = new Patient(uuid, personalData);

        Assert.assertEquals(patient1, patient2);
    }


    @Test
    public void testNotEquals() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());

        Patient patient1 = new Patient(UUID.randomUUID(), personalData);
        Patient patient2 = new Patient(UUID.randomUUID(), personalData);

        Assert.assertNotEquals(patient1, patient2);
    }


    @Test
    public void testHashCode() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());
        UUID uuid = UUID.randomUUID();

        Patient patient1 = new Patient(uuid, personalData);
        Patient patient2 = new Patient(uuid, personalData);

        Assert.assertEquals(patient1.hashCode(), patient2.hashCode());
    }


    @Test
    public void testNotHashCode() {
        PersonalData personalData = new PersonalData("firstname", "lastname", LocalDate.now());

        Patient patient1 = new Patient(UUID.randomUUID(), personalData);
        Patient patient2 = new Patient(UUID.randomUUID(), personalData);

        Assert.assertNotEquals(patient1.hashCode(), patient2.hashCode());
    }

}
