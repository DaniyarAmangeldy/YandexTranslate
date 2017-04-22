package daniyaramangeldy.yandextranslate.mvp.model;


import android.support.v4.util.ArrayMap;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.api.YandexTranslateApi;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmString;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class LanguageRepositoryImpl implements LanguageRepository {
    private YandexTranslateApi api;
    private String token;
    private Realm realm;

    @Inject
    public LanguageRepositoryImpl(YandexTranslateApi api, String token) {
        this.api = api;
        this.token = token;
    }


    @Override
    public Observable<List<Language>> getLanguages() {
        return api.getLanguages(token, "ru");
    }

    @Override
    public Observable<RealmTranslateResponse> translateText(String text, String lang) {
        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("key", token);
        params.put("text", text);
        params.put("lang", "ru-en");
        return api.getTranslate(params).doOnNext(this::addTranslate);
    }

    @Override
    public List<TranslateResponse> getHistory() {
        realm = Realm.getDefaultInstance();
        ArrayList<TranslateResponse> historyList = new ArrayList<>();
        RealmResults<RealmTranslateResponse> realmHistoryList =
                realm.where(RealmTranslateResponse.class).findAll();
        TranslateResponse response;
        ArrayList<String> stringList;
        for (RealmTranslateResponse realm : realmHistoryList) {
            stringList = new ArrayList<>();
            response = new TranslateResponse();
            for (RealmString realmString : realm.getText()) {
                stringList.add(realmString.getString());
            }
            response.setId(realm.getId());
            response.setLang(realm.getLang());
            response.setFavourite(realm.isFavourite());
            response.setText(stringList);
            historyList.add(response);
        }
        realm.close();
        Collections.reverse(historyList);
        return historyList;

    }

    private void addTranslate(RealmTranslateResponse realmTranslateResponse) {
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(realmTranslateResponse));
        realm.close();
    }
}
