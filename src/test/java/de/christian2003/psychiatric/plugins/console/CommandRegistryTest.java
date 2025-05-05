package de.christian2003.psychiatric.plugins.console;

import de.christian2003.psychiatric.application.console.*;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.PatientService;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;


public class CommandRegistryTest {

    @Test
    public void testGetCommand() {
        PatientService patientService = mock(PatientService.class);
        CrisisInterventionAreaService crisisInterventionAreaService = mock(CrisisInterventionAreaService.class);
        CommandRegistry registry = new CommandRegistry(patientService, crisisInterventionAreaService);

        Assert.assertTrue(registry.getCommand("create_patient") instanceof CreatePatientCommand);
        Assert.assertTrue(registry.getCommand("list_patients") instanceof ListPatientsCommand);
        Assert.assertTrue(registry.getCommand("delete_patient") instanceof DeletePatientCommand);
        Assert.assertTrue(registry.getCommand("edit_patient") instanceof EditPatientCommand);
        Assert.assertTrue(registry.getCommand("move_patient") instanceof MovePatientCommand);

        Assert.assertTrue(registry.getCommand("create_cia") instanceof CreateCiaCommand);
        Assert.assertTrue(registry.getCommand("list_cias") instanceof ListCiasCommand);
        Assert.assertTrue(registry.getCommand("delete_cia") instanceof DeleteCiaCommand);
        Assert.assertTrue(registry.getCommand("edit_cia") instanceof EditCiaCommand);

        Assert.assertTrue(registry.getCommand("help") instanceof HelpCommand);
    }

}
