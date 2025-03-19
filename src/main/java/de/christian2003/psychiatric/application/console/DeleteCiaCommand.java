package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
import de.christian2003.psychiatric.domain.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.services.ServiceException;

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
                ConsoleWriter.println("Invalid ID \"" + args.get("id") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'id'.", Colors.RED);
            return;
        }

        try {
            crisisInterventionAreaService.deleteCrisisInterventionArea(id);
        }
        catch (ServiceException e) {
            ConsoleWriter.println(e.getMessage(), Colors.RED);
            return;
        }

        ConsoleWriter.println("Deleted crisis intervention area.", Colors.DEFAULT);
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "delete cia",
                "Deletes a crisis intervention area.",
                "delete cia id=abe372e7-a249-4795-a2a6-ffe94a7993ce",
                Map.of(
                        "id", new ParameterInfo(true, "ID of the crisis intervention area to delete")
                )
        );
    }

}
