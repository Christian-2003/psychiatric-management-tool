package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;

import java.util.Map;
import java.util.UUID;

public class DeleteCiaCommand implements Command {

    private final CrisisInterventionAreaService crisisInterventionAreaService;


    public DeleteCiaCommand(CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
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

        CrisisInterventionArea crisisInterventionArea = crisisInterventionAreaService.getCrisisInterventionAreaById(id);
        if (crisisInterventionArea != null) {
            crisisInterventionAreaService.deleteCrisisInterventionArea(crisisInterventionArea);
            System.out.println("Deleted crisis intervention area " + crisisInterventionArea + ".");
        }
        else {
            System.out.println("Cannot delete crisis intervention area, since no crisis intervention area with ID \"" + args.get("id") + "\" exists.");
        }
    }

}
