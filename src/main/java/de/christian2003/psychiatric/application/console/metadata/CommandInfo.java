package de.christian2003.psychiatric.application.console.metadata;

import java.util.Map;

public class CommandInfo {

    private final String name;

    private final String description;

    private final String example;

    private final Map<String, ParameterInfo> parameters;


    public CommandInfo(String name, String description, String example, Map<String, ParameterInfo> parameters) throws NullPointerException {
        if (name == null || description == null || example == null || parameters == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.description = description;
        this.example = example;
        this.parameters = parameters;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExample() {
        return example;
    }

    public Map<String, ParameterInfo> getParameters() {
        return parameters;
    }

}
