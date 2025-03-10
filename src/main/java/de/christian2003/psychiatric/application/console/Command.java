package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.console.metadata.CommandInfo;

import java.util.Map;


public interface Command {

    void execute(Map<String, String> args);

    CommandInfo getInfo();

}
