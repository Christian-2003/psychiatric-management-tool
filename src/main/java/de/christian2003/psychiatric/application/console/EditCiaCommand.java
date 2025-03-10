package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.ServiceException;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;
import de.christian2003.psychiatric.domain.rooms.RoomData;

import java.util.Map;
import java.util.UUID;

public class EditCiaCommand implements Command {

    private final CrisisInterventionAreaService crisisInterventionAreaService;


    public EditCiaCommand(CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaService = crisisInterventionAreaService;
    }


    @Override
    public void execute(Map<String, String> args) {
        UUID id = null;
        if (args.containsKey("id")) {
            try {
                id = UUID.fromString(args.get("id"));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid ID \"" + args.get("id") + "\".");
                return;
            }
        }
        else {
            System.out.println("Missing argument 'id'.");
            return;
        }

        String name = null;
        if (args.containsKey("name")) {
            name = args.get("name");
        }

        CrisisInterventionArea crisisInterventionArea;
        try {
            crisisInterventionArea = crisisInterventionAreaService.editCrisisInterventionArea(id, name);
        }
        catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Updated crisis intervention area " + crisisInterventionArea + ".");
    }

}
