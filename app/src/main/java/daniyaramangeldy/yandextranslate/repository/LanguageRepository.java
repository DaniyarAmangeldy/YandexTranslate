package daniyaramangeldy.yandextranslate.repository;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;


/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface LanguageRepository {

    Observable<List<Language>> getLanguages();

    TranslateResponse findTranslateInCache(String text, String lang);

    Observable<TranslateResponse> translateText(String text, String lang);

    List<TranslateResponse> getHistory();

    List<Favourite> getFavourites();

    boolean addFavourite(TranslateResponse response);

    boolean removeFavourite(String id);

    boolean clearHistory();

    TranslateResponse getLastRequest();
}
