package daniyaramangeldy.yandextranslate.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractorImpl;
import daniyaramangeldy.yandextranslate.repository.Repository;

/**
 * Модуль для UseCase Закладок(История,Избранные)
 */

@Module(includes = {LanguageRepositoryModule.class})
public class BookmarksInteractorModule {

    @Provides@Singleton
    BookmarksInteractor getInteractor(Repository repository){
        return new BookmarksInteractorImpl(repository);
    }
}
