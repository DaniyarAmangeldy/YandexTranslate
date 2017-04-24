package daniyaramangeldy.yandextranslate.interactor;

import android.content.SharedPreferences;
import android.content.res.Resources;


import java.util.List;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import daniyaramangeldy.yandextranslate.repository.LanguageRepository;
import io.reactivex.Observable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class TranslateInteractorImpl implements TranslateInteractor {
    private final String KEY_LANG = "lang";
    private LanguageRepository langRepository;
    private SharedPreferences sp;
    private Resources res;

    @Inject
    public TranslateInteractorImpl(LanguageRepository langRepository, SharedPreferences sp, Resources res) {
        this.langRepository = langRepository;
        this.sp = sp;
        this.res = res;
    }

    public Observable<TranslateResponse> translateText(String text) {
        String lang = getLanguageFromSharedPreferences();
        return langRepository
                .translateText(text, lang);
    }


    public String[] getCurrentLanguage() {
        String[] result = getLanguageFromSharedPreferences().split("-");
        return new String[]{
                getLangByKey(result[0]),
                getLangByKey(result[1])
        };

    }


    public String[] getCurrentLanguageKey(){
        return getLanguageFromSharedPreferences().split("-");

    }

    private String getLangByKey(String s) {
        return langRepository.getLangByKey(s);
    }

    private String getLanguageFromSharedPreferences() {
        return sp.getString(KEY_LANG, "ru-en");
    }

    @Override
    public void swapLanguage() {
        String[] langs = getLanguageFromSharedPreferences().split("-");
        sp.edit()
                .putString(KEY_LANG, String.format("%s-%s", langs[1], langs[0]))
                .apply();
    }

    @Override
    public boolean setLanguage(String lang) {
        sp.edit()
                .putString(KEY_LANG,lang)
                .apply();
        return true;
    }

    @Override
    public Observable<LanguageMap> loadLanguages() {
        return langRepository.loadLanguages();
    }

    @Override
    public List<Language> getLanguageList() {
        return langRepository.getLanguageList();
    }


}
