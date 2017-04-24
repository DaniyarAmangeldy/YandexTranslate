package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface TranslateInteractor {

    Observable<TranslateResponse> translateText(String text);

    String[] getCurrentLanguageKey();

    String[] getCurrentLanguage();

    void swapLanguage();

    boolean setLanguage(String lang);

    Observable<LanguageMap> loadLanguages();

    List<Language> getLanguageList();

}
