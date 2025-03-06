package de.christian2003.psychiatric.database.file.gson;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString(), formatter);
    }

}
