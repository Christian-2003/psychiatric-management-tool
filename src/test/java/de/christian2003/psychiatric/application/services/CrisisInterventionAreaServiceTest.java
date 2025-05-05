package de.christian2003.psychiatric.application.services;

import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.domain.rooms.RoomData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CrisisInterventionAreaServiceTest {

    private final List<CrisisInterventionArea> crisisInterventionAreas = List.of(
            new CrisisInterventionArea.Builder(UUID.randomUUID(), new RoomData("Room 1")).build(),
            new CrisisInterventionArea.Builder(UUID.randomUUID(), new RoomData("Room 2")).assignPatient(UUID.randomUUID()).build()
    );

    private CrisisInterventionAreaService crisisInterventionAreaService;


    @Before
    public void createPatientService() {
        CrisisInterventionAreaRepository crisisInterventionAreaRepository = mock(CrisisInterventionAreaRepository.class);

        when(crisisInterventionAreaRepository.getAllCrisisInterventionAreas()).thenReturn(crisisInterventionAreas);
        when(crisisInterventionAreaRepository.getCrisisInterventionAreaById(any())).thenReturn(crisisInterventionAreas.get(0));

        crisisInterventionAreaService =  new CrisisInterventionAreaService(crisisInterventionAreaRepository);
    }


    @Test(expected = NullPointerException.class)
    public void testCreateWithoutRepositories() {
        new CrisisInterventionAreaService(null);
    }


    @Test
    public void testGetAllPatients() {
        List<CrisisInterventionArea> crisisInterventionAreas = crisisInterventionAreaService.getAllCrisisInterventionAreas();
        Assert.assertEquals(crisisInterventionAreas, this.crisisInterventionAreas);
    }


    @Test
    public void testEditCrisisInterventionArea() throws ServiceException {
        crisisInterventionAreaService.editCrisisInterventionArea(UUID.randomUUID(), "A265");
    }


    @Test(expected = NullPointerException.class)
    public void testEditCrisisInterventionAreaWithoutParameters() throws ServiceException {
        crisisInterventionAreaService.editCrisisInterventionArea(null, null);
    }


    @Test
    public void testDeleteCrisisInterventionArea() throws ServiceException {
        crisisInterventionAreaService.deleteCrisisInterventionArea(UUID.randomUUID());
    }


    @Test(expected = NullPointerException.class)
    public void testDeleteCrisisInterventionAreaWithoutParameter() throws ServiceException {
        crisisInterventionAreaService.deleteCrisisInterventionArea(null);
    }

}
