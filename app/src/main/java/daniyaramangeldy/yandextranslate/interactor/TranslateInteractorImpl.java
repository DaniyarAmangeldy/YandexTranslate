package daniyaramangeldy.yandextranslate.interactor;

import android.content.SharedPreferences;
import android.content.res.Resources;


import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.R;
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
                getLangByUi(result[0]),
                getLangByUi(result[1])
        };

    }

    private String getLangByUi(String s) {
        String result = "";
        switch (s) {
            case "ru":
                result = res.getString(R.string.string_lang_russian);
                break;
            case "en":
                result = res.getString(R.string.string_lang_english);
                break;
        }
        return result;
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


}
