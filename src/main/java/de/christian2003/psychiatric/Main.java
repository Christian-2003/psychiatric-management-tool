package de.christian2003.psychiatric;

import de.christian2003.psychiatric.adapters.console.ConsoleAdapter;
import de.christian2003.psychiatric.adapters.repositories.FileCrisisInterventionAreaRepository;
import de.christian2003.psychiatric.adapters.repositories.FileNurseRepository;
import de.christian2003.psychiatric.adapters.repositories.FileOfficeRepository;
import de.christian2003.psychiatric.adapters.repositories.FilePatientRepository;
import de.christian2003.psychiatric.application.repositories.*;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.NurseService;
import de.christian2003.psychiatric.application.services.OfficeService;
import de.christian2003.psychiatric.application.services.PatientService;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        //Create repositories and services:
        PatientRepository patientRepository = new FilePatientRepository();
        NurseRepository nurseRepository = new FileNurseRepository();
        OfficeRepository officeRepository = new FileOfficeRepository();
        CrisisInterventionAreaRepository crisisInterventionAreaRepository = new FileCrisisInterventionAreaRepository();

        PatientService patientService = new PatientService(patientRepository);
        NurseService nurseService = new NurseService(nurseRepository);
        OfficeService officeService = new OfficeService(officeRepository);
        CrisisInterventionAreaService crisisInterventionAreaService = new CrisisInterventionAreaService(crisisInterventionAreaRepository);


        //Load data if possible:
        try {
            if (patientRepository instanceof SavableRepository savableRepository) {
                savableRepository.loadData();
            }
            if (nurseRepository instanceof SavableRepository savableRepository) {
                savableRepository.loadData();
            }
            if (officeRepository instanceof SavableRepository savableRepository) {
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
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(patientService, nurseService, officeService, crisisInterventionAreaService);
        consoleAdapter.start();


        //Save data:
        try {
            if (patientRepository instanceof SavableRepository savableRepository) {
                savableRepository.saveData();
            }
            if (nurseRepository instanceof SavableRepository savableRepository) {
                savableRepository.saveData();
            }
            if (officeRepository instanceof SavableRepository savableRepository) {
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
