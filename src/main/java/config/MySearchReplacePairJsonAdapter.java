package config;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class MySearchReplacePairJsonAdapter extends TypeAdapter<MySearchReplacePair> {

    @Override
    public void write(JsonWriter writer, MySearchReplacePair mySearchReplacePair) throws IOException {

        writer.beginObject();

        writer.name("search").value(mySearchReplacePair.getSearch());
        writer.name("replace").value(mySearchReplacePair.getReplace());

        writer.endObject();

    }

    @Override
    public MySearchReplacePair read(JsonReader reader) throws IOException {

        MySearchReplacePair mySearchReplacePair = new MySearchReplacePair("", "");

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("search")) {
                String value = reader.nextString();
                mySearchReplacePair.setMySearch(value);
            }

            if (name.equals("replace")) {
                String value = reader.nextString();
                mySearchReplacePair.setMyReplace(value);
            }

        }

        reader.endObject();

        return mySearchReplacePair;
    }

}
