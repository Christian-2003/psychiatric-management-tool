package de.christian2003.psychiatric.application.console;

import java.util.Map;


public interface Command {

    void execute(Map<String, String> args);

}
