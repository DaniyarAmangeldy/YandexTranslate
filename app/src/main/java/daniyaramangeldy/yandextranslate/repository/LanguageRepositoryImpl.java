package daniyaramangeldy.yandextranslate.repository;


import android.support.v4.util.ArrayMap;
import android.util.Log;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.api.YandexTranslateApi;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmFavourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmLanguage;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Repository
 */

public class LanguageRepositoryImpl implements LanguageRepository {
    
    private YandexTranslateApi api;
    private String token;
    private String lang;

    @Inject
    public LanguageRepositoryImpl(YandexTranslateApi api, String token) {
        this.api = api;
        this.token = token;
    }


    @Override
    public Observable<LanguageMap> loadLanguages(String lang) {
        Realm realm = Realm.getDefaultInstance();
        this.lang = lang;

        if(realm.where(RealmLanguage.class).equalTo("lang",lang).findAll().size()!=0){
            realm.close();
            return Observable.just(new LanguageMap());
        }

        return api.getLanguages(token,lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setLangsToCache);
    }

    private void setLangsToCache(LanguageMap languageMap) {
        Realm realm = Realm.getDefaultInstance();
        try {

            if(realm.where(RealmLanguage.class).equalTo("lang",lang).findAll().size()==0) {
                realm.executeTransaction(realm1 -> {
                    for (Map.Entry<String, String> entry : languageMap.getLangMap().entrySet()) {
                        RealmLanguage realmLanguage = realm1.createObject(RealmLanguage.class);
                        realmLanguage.setId_(entry.getKey());
                        realmLanguage.setName(entry.getValue());
                        realmLanguage.setLang(lang);
                    }
                });

            }
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            realm.close();
        }
    }

    @Override
    public TranslateResponse findTranslateInCache(String text, String lang) {
        Realm realm = Realm.getDefaultInstance();
        RealmFavourite favourite = findTranslateFromFavourite(realm, text, lang);
        if (favourite != null) {
            TranslateResponse result = parseTranslateFromFavourite(favourite);
            realm.close();
            return result;
        } else {
            RealmTranslateResponse response = findTranslateFromHistory(realm, text, lang);
            if (response != null) {
                TranslateResponse result = parseTranslateFromRealmObject(response);
                realm.close();
                return result;
            }
            realm.close();
            return null;
        }
    }

    private RealmFavourite findTranslateFromFavourite(Realm realm, String text, String lang) {
        RealmFavourite favourite = realm.where(RealmFavourite.class)
                .equalTo("originalText", text)
                .equalTo("lang", lang)
                .findFirst();
        return favourite;
    }

    private RealmTranslateResponse findTranslateFromHistory(Realm realm, String text, String lang) {
        RealmTranslateResponse response = realm.where(RealmTranslateResponse.class)
                .equalTo("originalText", text)
                .equalTo("lang", lang)
                .findFirst();
        return response;
    }


    @Override
    public Observable<TranslateResponse> translateText(String text, String lang) {
        TranslateResponse cacheResponse = findTranslateInCache(text, lang);
        if (cacheResponse != null) {
            return Observable.just(cacheResponse)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

        }

        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("key", token);
        params.put("text", text);
        params.put("lang", lang);

        return api.getTranslate(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(translateResponse -> addTranslate(translateResponse, text))
                .map(this::parseTranslateFromRealmObject);
    }

    @Override
    public List<Favourite> getFavourites() {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<Favourite> favouriteList = new ArrayList<>();
        RealmResults<RealmFavourite> realmHistoryList =
                realm.where(RealmFavourite.class).findAll();

        for (RealmFavourite response : realmHistoryList) {

            favouriteList.add(parseFavouriteFromRealmObject(response));
        }
        realm.close();
        Collections.reverse(favouriteList);
        return favouriteList;
    }

    @Override
    public boolean addFavourite(TranslateResponse response) {
        Realm realm = Realm.getDefaultInstance();
        RealmFavourite favouriteCheck = realm.where(RealmFavourite.class)
                .equalTo("originalText", response.getOriginalText())
                .findFirst();
        if (favouriteCheck == null) {
            try {
                realm.executeTransaction(realm1 -> {
                    RealmFavourite favourite = realm1.createObject(RealmFavourite.class, UUID.randomUUID().toString());
                    favourite.setText(response.getText());
                    favourite.setLang(response.getLang());
                    favourite.setOriginalText(response.getOriginalText());
                    favourite.setFavourite(true);
                    RealmTranslateResponse cache = realm1.where(RealmTranslateResponse.class)
                            .equalTo("originalText", response.getOriginalText())
                            .findFirst();
                    if (cache != null) {
                        cache.setFavourite(true);
                    }
                });
                realm.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeFavourite(String text) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(realm1 -> {
                RealmFavourite favourite = realm1.where(RealmFavourite.class)
                        .equalTo("originalText", text)
                        .findFirst();
                if (favourite != null) {
                    favourite.deleteFromRealm();
                }
                RealmTranslateResponse response = realm1.where(RealmTranslateResponse.class)
                        .equalTo("originalText", text)
                        .findFirst();
                if (response != null) {
                    response.setFavourite(false);
                }
            });

            realm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Favourite getFavourite(String text) {
        Realm realm = Realm.getDefaultInstance();
        RealmFavourite realmFavourite = realm.where(RealmFavourite.class)
                .equalTo("originalText",text)
                .findFirst();
        if(realmFavourite!=null){
            Favourite result = parseFavouriteFromRealmObject(realmFavourite);
            realm.close();
            return result;
        }

        return null;
    }

    @Override
    public TranslateResponse getHistory(String text) {
        try {
            Realm realm = Realm.getDefaultInstance();
            RealmTranslateResponse realmResponse = realm.where(RealmTranslateResponse.class)
                    .equalTo("originalText", text)
                    .findFirst();
            if (realmResponse != null) {
                TranslateResponse result = parseTranslateFromRealmObject(realmResponse);
                realm.close();
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Language> getLanguageList(String lang) {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmLanguage> realmLangList = realm.where(RealmLanguage.class).equalTo("lang",lang).findAll();
            List<Language> langList = new ArrayList<>();
            for (RealmLanguage realmLang : realmLangList) {
                langList.add(parseLangFromRealm(realmLang));
            }
            realm.close();
            return langList;

        }catch (Exception e){
            realm.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getLangByKey(String key,String lang) {
        String result = "";
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmLanguage realmLang = realm.where(RealmLanguage.class).equalTo("id_", key).equalTo("lang",lang).findFirst();
            if (realmLang != null) {
                result = realmLang.getName();
            }
            realm.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            realm.close();
            return "";
        }
    }

    private Language parseLangFromRealm(RealmLanguage realmLang) {
        Language lang = new Language();
        lang.setId_(realmLang.getId_());
        lang.setName(realmLang.getName());
        return lang;
    }

    @Override
    public List<TranslateResponse> getHistoryList() {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<TranslateResponse> historyList = new ArrayList<>();
        RealmResults<RealmTranslateResponse> realmHistoryList =
                realm.where(RealmTranslateResponse.class).findAll();

        for (RealmTranslateResponse response : realmHistoryList) {

            historyList.add(parseTranslateFromRealmObject(response));
        }
        realm.close();
        Collections.reverse(historyList);
        return historyList;

    }

    @Override
    public boolean clearHistory() {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(realm1 ->
                    realm1.where(RealmTranslateResponse.class).findAll().deleteAllFromRealm());
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeHistory(String text) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(realm1 -> {
                RealmTranslateResponse result = realm1
                        .where(RealmTranslateResponse.class)
                        .equalTo("originalText", text).findFirst();
                if (result != null) {
                    result.deleteFromRealm();
                }
            });
            realm.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            realm.close();
            return false;
        }
    }

    @Override
    public TranslateResponse getLastRequest() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmTranslateResponse> translateList = realm.where(RealmTranslateResponse.class).findAll();
        if(translateList!=null && translateList.size()!=0) {
            TranslateResponse result = parseTranslateFromRealmObject(translateList.last());
            realm.close();
            return result;
        }

        return null;
    }

    private TranslateResponse parseTranslateFromRealmObject(RealmTranslateResponse realmResponse) {

        TranslateResponse response = new TranslateResponse();
        response.setId(realmResponse.getId());
        response.setLang(realmResponse.getLang());
        response.setFavourite(realmResponse.isFavourite());
        response.setText(realmResponse.getText().get(0).getString());
        response.setOriginalText(realmResponse.getOriginalText());
        return response;
    }

    private Favourite parseFavouriteFromRealmObject(RealmFavourite realmResponse) {

        Favourite response = new Favourite();
        response.setId(realmResponse.getId());
        response.setLang(realmResponse.getLang());
        response.setFavourite(realmResponse.isFavourite());
        response.setText(realmResponse.getText());
        response.setOriginalText(realmResponse.getOriginalText());
        return response;
    }

    private TranslateResponse parseTranslateFromFavourite(RealmFavourite favourite) {
        TranslateResponse translateResponse = new TranslateResponse();
        translateResponse.setId(favourite.getId());
        translateResponse.setOriginalText(favourite.getOriginalText());
        translateResponse.setLang(favourite.getLang());
        translateResponse.setFavourite(favourite.isFavourite());
        translateResponse.setText(favourite.getText());
        return translateResponse;
    }

    private void addTranslate(RealmTranslateResponse realmTranslateResponse, String originalText) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realmTranslateResponse.setOriginalText(originalText);
            realm1.copyToRealmOrUpdate(realmTranslateResponse);
        });
        realm.close();
    }


}
