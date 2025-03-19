package de.christian2003.psychiatric;

import de.christian2003.psychiatric.plugins.repositories.FileCrisisInterventionAreaRepository;
import de.christian2003.psychiatric.plugins.repositories.FilePatientRepository;
import de.christian2003.psychiatric.application.PsychiatricManagementTool;
import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.repositories.PatientRepository;


public class PsychiatricManagementToolStarter {

    public static void main(String[] args) {
        //Create repositories and services:
        PatientRepository patientRepository = new FilePatientRepository();
        CrisisInterventionAreaRepository crisisInterventionAreaRepository = new FileCrisisInterventionAreaRepository();

        PsychiatricManagementTool psychiatricManagementTool = new PsychiatricManagementTool(patientRepository, crisisInterventionAreaRepository);
        psychiatricManagementTool.startApp();
    }

}
