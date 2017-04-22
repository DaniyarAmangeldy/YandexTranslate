package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public interface BookmarksInteractor {
    List<TranslateResponse> getHistory();
}
