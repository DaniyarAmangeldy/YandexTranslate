package daniyaramangeldy.yandextranslate.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.api.YandexTranslateApi;
import retrofit2.Retrofit;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public YandexTranslateApi getApi(Retrofit retrofit){
        return retrofit.create(YandexTranslateApi.class);
    }
}
