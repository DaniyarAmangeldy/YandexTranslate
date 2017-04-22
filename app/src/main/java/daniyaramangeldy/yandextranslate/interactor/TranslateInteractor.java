package daniyaramangeldy.yandextranslate.interactor;

import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import io.reactivex.Observable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface TranslateInteractor {

    Observable<RealmTranslateResponse> translateText(String text);

}
