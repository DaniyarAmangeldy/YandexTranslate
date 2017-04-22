package daniyaramangeldy.yandextranslate.di.modules;

import android.content.res.Resources;

import com.activeandroid.query.Select;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.api.YandexTranslateApi;
import daniyaramangeldy.yandextranslate.mvp.model.LanguageRepository;
import daniyaramangeldy.yandextranslate.mvp.model.LanguageRepositoryImpl;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

@Module(includes = {ApiModule.class, DataBaseModule.class,AppModule.class})
public class LanguageRepositoryModule {

    @Provides@Singleton
    public LanguageRepository getLanguageRepository(YandexTranslateApi api, Select select, Resources res){
        return new LanguageRepositoryImpl(api,select,res.getString(R.string.api_key));
    }
}
