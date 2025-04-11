package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;

import java.util.Map;

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
            ConsoleWriter.println("Missing argument 'name'.", Colors.RED);
            return;
        }

        crisisInterventionAreaService.createCrisisInterventionArea(name);
        ConsoleWriter.println("Created crisis intervention area.", Colors.DEFAULT);
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "create cia",
                "Creates a new crisis intervention area.",
                "create cia name=WhiteHouse",
                Map.of(
                        "name", new ParameterInfo(true, "Name for the crisis intervention area")
                )
        );
    }

}
