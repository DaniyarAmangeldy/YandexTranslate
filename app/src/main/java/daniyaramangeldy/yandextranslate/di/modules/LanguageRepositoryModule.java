package daniyaramangeldy.yandextranslate.di.modules;

import android.content.res.Resources;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.api.YandexTranslateApi;
import daniyaramangeldy.yandextranslate.repository.Repository;
import daniyaramangeldy.yandextranslate.repository.RepositoryImpl;

/**
 * Модуль для Репозитория
 */

@Module(includes = {ApiModule.class, AppModule.class})
public class LanguageRepositoryModule {

    @Provides@Singleton
    public Repository getLanguageRepository(YandexTranslateApi api, Resources res){
        return new RepositoryImpl(api,res.getString(R.string.api_key));
    }
}
