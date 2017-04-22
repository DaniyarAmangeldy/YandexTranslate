package daniyaramangeldy.yandextranslate.mvp.model;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;


/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface LanguageRepository {

    Observable<List<Language>> getLanguages();

    Observable<TranslateResponse> translateText(String text, String lang);
}
