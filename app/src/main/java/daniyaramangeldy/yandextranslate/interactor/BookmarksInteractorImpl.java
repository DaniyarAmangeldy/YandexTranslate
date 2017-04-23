package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;


import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.repository.LanguageRepository;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public class BookmarksInteractorImpl implements BookmarksInteractor {

    private LanguageRepository repository;

    public BookmarksInteractorImpl(LanguageRepository repository){
        this.repository = repository;
    }

    @Override
    public List<TranslateResponse> getHistory() {
        return repository.getHistory();
    }

    @Override
    public boolean clearHistory() {
        return repository.clearHistory();
    }



    @Override
    public boolean addFavourite(TranslateResponse response) {
        return repository.addFavourite(response);
    }

    @Override
    public boolean removeFavourite(String name) {
        return repository.removeFavourite(name);
    }

    @Override
    public List<Favourite> getFavourites() {
        return repository.getFavourites();
    }

    @Override
    public TranslateResponse getLastRequest() {
        return repository.getLastRequest();
    }
}
