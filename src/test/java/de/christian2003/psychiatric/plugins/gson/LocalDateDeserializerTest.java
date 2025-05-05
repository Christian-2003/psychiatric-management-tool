package de.christian2003.psychiatric.plugins.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;


public class LocalDateDeserializerTest {

    @Test
    public void testDeserialize() {
        JsonElement serialized = new JsonPrimitive("2001-09-11");
        LocalDate date = new LocalDateDeserializer().deserialize(serialized, null, null);
        Assert.assertEquals(date, LocalDate.of(2001, 9, 11));
    }

}
