package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;


import daniyaramangeldy.yandextranslate.mvp.model.LanguageRepository;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public class BookmarksInteractorImpl implements BookmarksInteractor {

    LanguageRepository repository;

    public BookmarksInteractorImpl(LanguageRepository repository){
        this.repository = repository;
    }

    @Override
    public List<TranslateResponse> getHistory() {
        return repository.getHistory();
    }
}
