package daniyaramangeldy.yandextranslate.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractorImpl;
import daniyaramangeldy.yandextranslate.repository.LanguageRepository;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

@Module(includes = {LanguageRepositoryModule.class})
public class BookmarksInteractorModule {

    @Provides@Singleton
    BookmarksInteractor getInteractor(LanguageRepository repository){
        return new BookmarksInteractorImpl(repository);
    }
}
