package daniyaramangeldy.yandextranslate.interactor;

import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface TranslateInteractor {

    Observable<TranslateResponse> translateText(String text);

    String[] getCurrentLanguage();

    void swapLanguage();

}
