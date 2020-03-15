package config;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;

@JsonAdapter(MyNameLocationPairJsonAdapter.class)
public class MyNameLocationPair implements Serializable {

    String myName;
    String myLocation;

    public MyNameLocationPair(String name, String location) {
        myName = name;
        myLocation = location;
    }

    public String getName() {
        return myName;
    }

    public String getLocation(){
        return myLocation;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }
}
