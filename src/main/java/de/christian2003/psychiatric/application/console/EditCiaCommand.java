package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.ServiceException;

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
                ConsoleWriter.println("Invalid ID \"" + args.get("id") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'id'.", Colors.RED);
            return;
        }

        String name = null;
        if (args.containsKey("name")) {
            name = args.get("name");
        }

        try {
            crisisInterventionAreaService.editCrisisInterventionArea(id, name);
        }
        catch (ServiceException e) {
            ConsoleWriter.println(e.getMessage(), Colors.RED);
            return;
        }

        ConsoleWriter.println("Updated crisis intervention area", Colors.DEFAULT);
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "edit cia",
                "Edits a crisis intervention area.",
                "edit cia id=abe372e7-a249-4795-a2a6-ffe94a7993ce name=US-Congress",
                Map.of(
                        "id", new ParameterInfo(true, "ID of the crisis intervention area to edit"),
                        "name", new ParameterInfo(false, "New name for the crisis intervention area")
                )
        );
    }

}
