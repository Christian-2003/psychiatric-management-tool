package de.christian2003.psychiatric.application.console.metadata;

public class ParameterInfo {

    private final boolean required;

    private final String description;


    public ParameterInfo(boolean required, String description) throws NullPointerException {
        if (description == null) {
            throw new NullPointerException();
        }
        this.required = required;
        this.description = description;
    }


    public boolean isRequired() {
        return required;
    }

    public String getDescription() {
        return description;
    }

}
