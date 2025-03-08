package de.christian2003.psychiatric.application.repositories;


import java.io.IOException;

public interface SavableRepository {

    void saveData() throws IOException;

    void loadData() throws IOException;

}
