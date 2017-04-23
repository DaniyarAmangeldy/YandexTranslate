package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public interface BookmarksInteractor {
    List<TranslateResponse> getHistoryList();
    boolean clearHistory();
    boolean addFavourite(TranslateResponse response);
    boolean removeFavourite(String name);
    boolean removeHistory(String text);
    List<Favourite> getFavourites();
    TranslateResponse getLastRequest();
    Favourite getFavourite(String text);
    TranslateResponse getHistory(String text);
}
