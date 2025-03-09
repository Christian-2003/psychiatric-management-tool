package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;

import java.util.List;
import java.util.Map;

public class ListCiasCommand implements Command {

    private final CrisisInterventionAreaService crisisInterventionAreaService;


    public ListCiasCommand(CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaService = crisisInterventionAreaService;
    }


    @Override
    public void execute(Map<String, String> args) {
        if (!args.isEmpty()) {
            System.out.println("Invalid arguments.");
            return;
        }

        List<CrisisInterventionArea> crisisInterventionAreas = crisisInterventionAreaService.getAllCrisisInterventionAreas();
        for (CrisisInterventionArea crisisInterventionArea: crisisInterventionAreas) {
            System.out.println(crisisInterventionArea);
        }
        if (crisisInterventionAreas.isEmpty()) {
            System.out.println("No crisis intervention areas available.");
        }
    }

}
