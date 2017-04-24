package daniyaramangeldy.yandextranslate.repository;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;


/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface LanguageRepository {

    Observable<LanguageMap> loadLanguages(String lang);

    TranslateResponse findTranslateInCache(String text, String lang);

    Observable<TranslateResponse> translateText(String text, String lang);

    List<TranslateResponse> getHistoryList();

    List<Favourite> getFavourites();

    boolean addFavourite(TranslateResponse response);

    boolean removeFavourite(String id);

    boolean clearHistory();

    boolean removeHistory(String text);

    TranslateResponse getLastRequest();

    Favourite getFavourite(String text);
    TranslateResponse getHistory(String text);

    List<Language> getLanguageList(String lang);
    String getLangByKey(String key,String lang);
}
