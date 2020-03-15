package config;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class MyNameLocationPairJsonAdapter extends TypeAdapter<MyNameLocationPair> {

    @Override
    public void write(JsonWriter writer, MyNameLocationPair myNameLocationPair) throws IOException {

        writer.beginObject();

        writer.name("name").value(myNameLocationPair.getName());
        writer.name("location").value(myNameLocationPair.getLocation());

        writer.endObject();

    }

    @Override
    public MyNameLocationPair read(JsonReader reader) throws IOException {

        MyNameLocationPair myNameLocationPair = new MyNameLocationPair("", "");

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("name")) {
                String value = reader.nextString();
                myNameLocationPair.setMyName(value);
            }

            if (name.equals("location")) {
                String value = reader.nextString();
                myNameLocationPair.setMyLocation(value);
            }

        }

        reader.endObject();

        return myNameLocationPair;
    }

}
