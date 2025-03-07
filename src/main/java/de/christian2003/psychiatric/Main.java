package de.christian2003.psychiatric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.christian2003.psychiatric.plugins.gson.LocalDateDeserializer;
import de.christian2003.psychiatric.plugins.gson.LocalDateSerializer;
import de.christian2003.psychiatric.domain.people.PersonalData;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        PersonalData data = new PersonalData("Christian", "Steinbring", LocalDate.now());

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = builder.create();
        String json = gson.toJson(data);
        System.out.println(json);
        PersonalData deserialized = gson.fromJson(json, PersonalData.class);
        System.out.println(deserialized.getFirstname());
        System.out.println(deserialized.getLastname());
        System.out.println(deserialized.getBirthday());
    }

}
