package de.christian2003.psychiatric;

import de.christian2003.psychiatric.adapters.console.ConsoleAdapter;
import de.christian2003.psychiatric.adapters.repositories.FileCrisisInterventionAreaRepository;
import de.christian2003.psychiatric.adapters.repositories.FilePatientRepository;
import de.christian2003.psychiatric.application.repositories.*;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.PatientService;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        //Create repositories and services:
        PatientRepository patientRepository = new FilePatientRepository();
        CrisisInterventionAreaRepository crisisInterventionAreaRepository = new FileCrisisInterventionAreaRepository();

        PatientService patientService = new PatientService(patientRepository, crisisInterventionAreaRepository);
        CrisisInterventionAreaService crisisInterventionAreaService = new CrisisInterventionAreaService(crisisInterventionAreaRepository);


        //Load data if possible:
        try {
            if (patientRepository instanceof SavableRepository savableRepository) {
                savableRepository.loadData();
            }
            if (crisisInterventionAreaRepository instanceof SavableRepository savableRepository) {
                savableRepository.loadData();
            }
        }
        catch (IOException e) {
            System.out.println("Cannot load data");
            return;
        }


        //Create console:
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(patientService, crisisInterventionAreaService);
        consoleAdapter.start();


        //Save data:
        try {
            if (patientRepository instanceof SavableRepository savableRepository) {
                savableRepository.saveData();
            }
            if (crisisInterventionAreaRepository instanceof SavableRepository savableRepository) {
                savableRepository.saveData();
            }
        }
        catch (IOException e) {
            System.out.println("Cannot save data");
        }
    }

}
