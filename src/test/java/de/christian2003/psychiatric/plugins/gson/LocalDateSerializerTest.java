package de.christian2003.psychiatric.plugins.gson;

import com.google.gson.JsonElement;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class LocalDateSerializerTest {

    @Test
    public void testSerialize() {
        LocalDate date = LocalDate.of(2001, 9, 11);
        JsonElement serialized = new LocalDateSerializer().serialize(date, null, null);
        Assert.assertEquals("2001-09-11", serialized.getAsString());
    }

}
