package daniyaramangeldy.yandextranslate.mvp.model.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmString;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import io.realm.RealmList;

/**
 * Маппер для Обьекта Перевода
 */

public class TranslateMapper implements JsonDeserializer<RealmTranslateResponse> {
    @Override
    public RealmTranslateResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RealmTranslateResponse response = new RealmTranslateResponse();
        JsonObject object = json.getAsJsonObject();
        JsonArray textArray = object.get("text").getAsJsonArray();
        response.setLang(object.get("lang").getAsString());
        RealmList<RealmString> translateTextArray = new RealmList<>();
        for(JsonElement element : textArray){
            RealmString string = new RealmString(element.getAsString());
            translateTextArray.add(string);
        }
        response.setText(translateTextArray);
        return response;
    }
}
