package de.christian2003.psychiatric.plugins;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.christian2003.psychiatric.plugins.gson.LocalDateDeserializer;
import de.christian2003.psychiatric.plugins.gson.LocalDateSerializer;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileSerializer<T> {

    private final Gson gson;

    private final Type type;

    private final String fileName;


    public FileSerializer(String fileName, Class<T> clazz) throws NullPointerException {
        if (fileName == null) {
            throw new NullPointerException();
        }
        this.fileName = fileName;

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gson = builder.create();

        type = TypeToken.getParameterized(List.class, clazz).getType();
        //type = new TypeToken<List<T>>() {}.getType();
    }


    public void saveAll(List<T> items) throws NullPointerException, IOException {
        if (items == null) {
            throw new NullPointerException();
        }

        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try {
            String json = gson.toJson(items, type);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(json);
            }
        }
        catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }


    public List<T> loadAll() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                StringBuilder json = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    while (reader.ready()) {
                        json.append(reader.readLine());
                    }
                }
                if (!json.isEmpty()) {
                    return gson.fromJson(json.toString(), type);
                }
                return new ArrayList<>();
            }
            catch (Exception e) {
                throw new IOException(e.getMessage());
            }
        }
        else {
            return new ArrayList<>();
        }
    }

}
