package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.domain.rooms.RoomData;

import java.util.Map;
import java.util.UUID;

public class CreateCiaCommand implements Command {

    private final CrisisInterventionAreaService crisisInterventionAreaService;


    public CreateCiaCommand(CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaService = crisisInterventionAreaService;
    }


    @Override
    public void execute(Map<String, String> args) {
        String name = null;
        if (args.containsKey("name")) {
            name = args.get("name");
        }
        else {
            System.out.println("Missing argument 'name'.");
            return;
        }

        CrisisInterventionArea crisisInterventionArea = crisisInterventionAreaService.createCrisisInterventionArea(name);
        System.out.println("Created crisis intervention area " + crisisInterventionArea + ".");
    }

}
