package de.christian2003.psychiatric.application.services;

import static org.mockito.Mockito.*;

import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;
import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.repositories.PatientRepository;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.domain.rooms.RoomData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.*;


public class PatientServiceTest {

    private final List<Patient> patients = List.of(
            new Patient(UUID.randomUUID(), new PersonalData("John", "Doe", LocalDate.now())),
            new Patient(UUID.randomUUID(), new PersonalData("firstname", "lastname", LocalDate.now()))
    );

    private final List<CrisisInterventionArea> crisisInterventionAreas = List.of(
            new CrisisInterventionArea.Builder(UUID.randomUUID(), new RoomData("Room 1")).build(),
            new CrisisInterventionArea.Builder(UUID.randomUUID(), new RoomData("Room 2")).assignPatient(UUID.randomUUID()).build()
    );

    private PatientService patientService;


    @Before
    public void createPatientService() {
        PatientRepository patientRepository = mock(PatientRepository.class);
        CrisisInterventionAreaRepository crisisInterventionAreaRepository = mock(CrisisInterventionAreaRepository.class);

        when(patientRepository.getAllPatients()).thenReturn(patients);
        when(patientRepository.getPatientById(any())).thenReturn(patients.get(0));
        when(crisisInterventionAreaRepository.getAllCrisisInterventionAreas()).thenReturn(crisisInterventionAreas);
        when(crisisInterventionAreaRepository.getCrisisInterventionAreaById(any())).thenReturn(crisisInterventionAreas.get(0));

        patientService =  new PatientService(patientRepository, crisisInterventionAreaRepository);
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutRepositories() {
        new PatientService(null, null);
    }


    @Test
    public void testGetAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        Assert.assertEquals(patients, this.patients);
    }


    @Test
    public void testGetPatientById() {
        Patient patient = patientService.getPatientById(UUID.randomUUID());
        Assert.assertEquals(patient, patients.get(0));
    }


    @Test
    public void testEditPatient() throws ServiceException {
        patientService.editPatient(UUID.randomUUID(), "firstname", "lastname", LocalDate.now());
    }


    @Test
    public void testDeletePatient() throws ServiceException {
        patientService.deletePatient(UUID.randomUUID());

    }


    @Test
    public void testMovePatient() throws ServiceException {
        patientService.movePatientToCrisisInterventionArea(patients.get(0).getPatientId(), crisisInterventionAreas.get(0).getRoomId());
    }


    @Test(expected = NullPointerException.class)
    public void testMovePatientWithoutId() throws ServiceException {
        patientService.movePatientToCrisisInterventionArea(null, null);
    }

}
