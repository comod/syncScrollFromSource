package config;

import application.Constants;
import com.google.gson.Gson;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.OptionTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.xmlb.Converter;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * This is a PersistentStateComponent.
 * Every Property in this class will be saved (on Application-Level)
 */
@State(
    name = ApplicationConfig.NAME,
    storages = { @Storage(ApplicationConfig.STORAGE) }
)

public class ApplicationConfig implements PersistentStateComponent<ApplicationConfig> {

    @OptionTag(converter = MyPairsConverter.class)
    public List<MyNameLocationPair> myPairs;
    static final String NAME = Constants.APPLICATION_NAME + "ApplicationConfig";
    static final String STORAGE = Constants.APPLICATION_NAME + NAME + ".xml";

    @Nullable
    @Override
    public ApplicationConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ApplicationConfig ApplicationConfig) {
        XmlSerializerUtil.copyBean(ApplicationConfig, this);
    }

//    @Nullable
//    public static ApplicationConfig getInstance() {
//        return ServiceManager.getService(ApplicationConfig.class);
//    }

    @Nullable
    public static ApplicationConfig getInstance(Project project) {
        return ServiceManager.getService(project, ApplicationConfig.class);
    }

    public List<MyNameLocationPair> getMyPairs() {
        return myPairs;
    }

    public void setMyPairs(List<MyNameLocationPair> myPairs) {
        this.myPairs = myPairs;
    }

}

class MyPairsConverter extends Converter<List<MyNameLocationPair>> {

    private final Gson gson;

    public MyPairsConverter(){
        gson = new Gson();
    }

    public List<MyNameLocationPair> fromString(@NotNull String payload) {

        try {
            return gson.fromJson(payload, new TypeToken<List<MyNameLocationPair>>() {}.getType());
        } catch (Exception err) {
            System.out.println("err" + err.getMessage());
        }

        return null;
        //        // Fallback
        //        List<MyNameLocationPair> myPairs = new ArrayList<>();
        //        myPairs.add(new MyNameLocationPair("", ""));
        //        return myPairs;
    }

    public String toString(@NotNull List<MyNameLocationPair> payload) {
        return gson.toJson(payload);
    }
}