package daniyaramangeldy.yandextranslate.di.modules;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.interactor.TranslateInteractor;
import daniyaramangeldy.yandextranslate.interactor.TranslateInteractorImpl;
import daniyaramangeldy.yandextranslate.mvp.model.LanguageRepository;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

@Module(includes = {LanguageRepositoryModule.class,AppModule.class})
public class TranslateInteractorModule {

    @Provides@Singleton
    public TranslateInteractor getTranslateInteractor(LanguageRepository repository, SharedPreferences sp){
        return new TranslateInteractorImpl(repository,sp);
    }
}
