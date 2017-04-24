package daniyaramangeldy.yandextranslate.mvp.model.mapper;

import android.support.v4.util.ArrayMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;

/**
 * Created by daniyaramangeldy on 24.04.17.
 */

public class LanguageMapper implements JsonDeserializer<LanguageMap> {
    @Override
    public LanguageMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        LanguageMap map = new LanguageMap();
        JsonObject object = json.getAsJsonObject().get("langs").getAsJsonObject();
        ArrayMap<String,String> langmap = new ArrayMap<>();
        for(Map.Entry<String,JsonElement> entry : object.entrySet()){
            String key =  entry.getKey();
            String value = object.get(key).getAsString();
            langmap.put(key,value);
        }
        map.setLangMap(langmap);
        return map;
    }
}
