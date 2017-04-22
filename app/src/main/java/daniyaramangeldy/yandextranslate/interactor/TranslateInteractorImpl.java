package daniyaramangeldy.yandextranslate.interactor;

import android.content.SharedPreferences;


import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.mvp.model.LanguageRepository;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import io.reactivex.Observable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class TranslateInteractorImpl implements TranslateInteractor {
    private LanguageRepository langRepository;
    private SharedPreferences sp;

    @Inject
    public TranslateInteractorImpl(LanguageRepository langRepository, SharedPreferences sp) {
        this.langRepository = langRepository;
        this.sp = sp;
    }

    public Observable<RealmTranslateResponse> translateText(String text) {
        String lang = getLanguage();
        return langRepository
                .translateText(text, lang);

    }
    private String getLanguage() {
        return sp.getString("lang", "ru");
    }


}
