package de.christian2003.psychiatric.application;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleAdapter;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.domain.repositories.CrisisInterventionAreaRepository;
import de.christian2003.psychiatric.domain.repositories.PatientRepository;
import de.christian2003.psychiatric.domain.repositories.SavableRepository;
import java.io.IOException;


public class PsychiatricManagementTool {

    private final PatientRepository patientRepository;

    private final CrisisInterventionAreaRepository crisisInterventionAreaRepository;



    public PsychiatricManagementTool(PatientRepository patientRepository, CrisisInterventionAreaRepository crisisInterventionAreaRepository) throws NullPointerException {
        if (patientRepository == null || crisisInterventionAreaRepository == null) {
            throw new NullPointerException();
        }
        this.patientRepository = patientRepository;
        this.crisisInterventionAreaRepository = crisisInterventionAreaRepository;
    }


    public void startApp() {
        PatientService patientService = new PatientService(patientRepository, crisisInterventionAreaRepository);
        CrisisInterventionAreaService crisisInterventionAreaService = new CrisisInterventionAreaService(crisisInterventionAreaRepository);

        ConsoleWriter.println("=====================================================", Colors.DEFAULT);
        ConsoleWriter.println("   Psychiatric Management Tool (PMT) version 1.0.0", Colors.DEFAULT);
        ConsoleWriter.println("=====================================================", Colors.DEFAULT);

        loadData();

        ConsoleWriter.println("Type 'help' for more information.", Colors.DEFAULT);
        ConsoleWriter.println("Type 'exit' to close the app and save data.", Colors.DEFAULT);

        //Create console:
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(patientService, crisisInterventionAreaService);
        consoleAdapter.start();

        saveData();
    }


    private void loadData() {
        try {
            if (patientRepository instanceof SavableRepository savableRepository) {
                savableRepository.loadData();
                ConsoleWriter.print("Loaded ", Colors.DEFAULT);
                ConsoleWriter.print("" + patientRepository.getAllPatients().size(), Colors.YELLOW);
                ConsoleWriter.println(" patient(s).", Colors.DEFAULT);


            }
            if (crisisInterventionAreaRepository instanceof SavableRepository savableRepository) {
                savableRepository.loadData();
                ConsoleWriter.print("Loaded ", Colors.DEFAULT);
                ConsoleWriter.print("" + crisisInterventionAreaRepository.getAllCrisisInterventionAreas().size(), Colors.YELLOW);
                ConsoleWriter.println(" crisis intervention area(s).", Colors.DEFAULT);
            }
        }
        catch (IOException e) {
            ConsoleWriter.println("Cannot load data: " + e.getMessage(), Colors.RED);
        }
    }


    private void saveData() {
        try {
            if (patientRepository instanceof SavableRepository savableRepository) {
                savableRepository.saveData();
                ConsoleWriter.print("Saved ", Colors.DEFAULT);
                ConsoleWriter.print("" + patientRepository.getAllPatients().size(), Colors.YELLOW);
                ConsoleWriter.println(" patient(s).", Colors.DEFAULT);
            }
            if (crisisInterventionAreaRepository instanceof SavableRepository savableRepository) {
                savableRepository.saveData();
                ConsoleWriter.print("Saved ", Colors.DEFAULT);
                ConsoleWriter.print("" + crisisInterventionAreaRepository.getAllCrisisInterventionAreas().size(), Colors.YELLOW);
                ConsoleWriter.println(" crisis intervention area(s).", Colors.DEFAULT);
            }
        }
        catch (IOException e) {
            ConsoleWriter.println("Cannot save data: " + e.getMessage(), Colors.RED);
        }
    }

}
