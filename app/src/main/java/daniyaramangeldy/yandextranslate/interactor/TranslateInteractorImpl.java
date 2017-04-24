package daniyaramangeldy.yandextranslate.interactor;

import android.content.SharedPreferences;
import android.content.res.Resources;


import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import daniyaramangeldy.yandextranslate.repository.Repository;
import io.reactivex.Observable;

/**
 * Имплементация Интерактора usecase Перевода.
 */

public class TranslateInteractorImpl implements TranslateInteractor {
    private final String KEY_LANG = "lang";
    private final String KEY_SYSTEM_LANG = "systemLang";

    private Repository langRepository;
    private SharedPreferences sp;
    private Resources res;

    @Inject
    public TranslateInteractorImpl(Repository langRepository, SharedPreferences sp, Resources res) {
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
        return langRepository.getLangByKey(s,getSystemLanguage());
    }

    //TODO: Получить Язык из SharedPreferences. Язык выбранный userом я храню в sharedPreferences
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
        String language = Locale.getDefault().getLanguage();
        if(!sp.getString(KEY_SYSTEM_LANG,"ru").equals(language));{
            editSystemLanguage(language);
            return langRepository.loadLanguages(language.substring(0,2).toLowerCase());
        }
    }

    //TODO: Изменяем в sharedPreferences СИСТЕМНЫЙ язык :)
    private void editSystemLanguage(String language) {
        sp.edit().putString(KEY_SYSTEM_LANG,language).apply();
    }


    @Override
    public List<Language> getLanguageList() {
        return langRepository.getLanguageList(getSystemLanguage());
    }


    public String getSystemLanguage() {
        return sp.getString(KEY_SYSTEM_LANG,"ru");
    }
}
