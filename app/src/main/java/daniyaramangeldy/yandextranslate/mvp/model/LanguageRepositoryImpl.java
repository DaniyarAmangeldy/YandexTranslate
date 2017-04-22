package daniyaramangeldy.yandextranslate.mvp.model;


import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.activeandroid.query.Select;

import java.util.List;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.api.YandexTranslateApi;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class LanguageRepositoryImpl implements LanguageRepository {
    private YandexTranslateApi api;
    private Select db;
    private String token;
    private final String LANG_RUSSIAN = "ru";
    private static final String TAG = "LanguageRepositoryImpl";

    @Inject
    public LanguageRepositoryImpl(YandexTranslateApi api, Select db,String token) {
        this.api = api;
        this.db = db;
        this.token = token;
    }


    @Override
    public Observable<List<Language>> getLanguages() {
            return api.getLanguages(token,LANG_RUSSIAN);
    }

    @Override
    public Observable<TranslateResponse> translateText(String text, String lang) {
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("key",token);
        params.put("text",text);
        params.put("lang","ru-en");
        return api.getTranslate(params);

    }

    private void putToDb(TranslateResponse translateResponse) {

    }
}
