package de.christian2003.psychiatric.domain.people;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;


public class PersonalDataTest {

    @Test
    public void testCreate() {
        LocalDate now = LocalDate.now();

        PersonalData personalData = new PersonalData("firstname", "lastname", now);

        Assert.assertEquals("firstname", personalData.getFirstname());
        Assert.assertEquals("lastname", personalData.getLastname());
        Assert.assertEquals(now.toEpochDay(), personalData.getBirthday().toEpochDay());
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutFirstname() {
        new PersonalData(null, "lastname", LocalDate.now());
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutLastname() {
        new PersonalData("firstname", null, LocalDate.now());
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutBirthday() {
        new PersonalData("firstname", "lastname", null);
    }


    @Test
    public void testEquals() {
        LocalDate now = LocalDate.now();

        PersonalData personalData1 = new PersonalData("firstname", "lastname", now);
        PersonalData personalData2 = new PersonalData("firstname", "lastname", now);

        Assert.assertEquals(personalData1, personalData2);
    }


    @Test
    public void testNotEquals() {
        LocalDate now = LocalDate.now();

        PersonalData personalData1 = new PersonalData("firstname", "lastname", now);
        PersonalData personalData2 = new PersonalData("john", "doe", now.plusYears(2));

        Assert.assertNotEquals(personalData1, personalData2);
    }


    @Test
    public void testHashCode() {
        LocalDate now = LocalDate.now();

        PersonalData personalData1 = new PersonalData("firstname", "lastname", now);
        PersonalData personalData2 = new PersonalData("firstname", "lastname", now);

        Assert.assertEquals(personalData1.hashCode(), personalData2.hashCode());
    }


    @Test
    public void testNotHashCode() {
        LocalDate now = LocalDate.now();

        PersonalData personalData1 = new PersonalData("firstname", "lastname", now);
        PersonalData personalData2 = new PersonalData("john", "doe", now.plusYears(2));

        Assert.assertNotEquals(personalData1.hashCode(), personalData2.hashCode());
    }

}
