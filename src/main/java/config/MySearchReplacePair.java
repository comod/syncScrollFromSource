package config;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;

@JsonAdapter(MySearchReplacePairJsonAdapter.class)
public class MySearchReplacePair implements Serializable {

    String mySearch;
    String myReplace;

    public MySearchReplacePair(String search, String replace) {
        mySearch = search;
        myReplace = replace;
    }

    public String getSearch() {
        return mySearch;
    }

    public String getReplace(){
        return myReplace;
    }

    public void setMySearch(String mySearch) {
        this.mySearch = mySearch;
    }

    public void setMyReplace(String myReplace) {
        this.myReplace = myReplace;
    }
}
