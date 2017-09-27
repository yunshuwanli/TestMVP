package yswl.com.testmvp.refresh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import yswl.com.klibrary.util.DateJsonDeserializer;

/**
 * Created by aspsine on 15/9/4.
 */
public class SectionCharacters {

    private List<Character> characters;
    private List<Section> sections;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public static SectionCharacters jsonToObj(JSONObject jsonStr) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                    new DateJsonDeserializer()).create();
            Type listum = new TypeToken<SectionCharacters>() {
            }.getType();
            SectionCharacters result = gson.fromJson(jsonStr.toString(), listum);
            return result;
        } catch (Exception e) {
        }
        return null;
    }
}
