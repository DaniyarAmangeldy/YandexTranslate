package daniyaramangeldy.yandextranslate.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.mapper.LanguageMapper;
import daniyaramangeldy.yandextranslate.mvp.model.mapper.TranslateMapper;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit модулируем тут :)
 */

@Module
public class RetrofitModule {

    @Provides@Singleton
    public Retrofit getRetrofit(Retrofit.Builder builder){
        return builder.baseUrl("https://translate.yandex.net/").build();
    }

    @Provides@Singleton
    public OkHttpClient getClient(){
        return new OkHttpClient().newBuilder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Provides@Singleton
    public Converter.Factory  getMapperFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides@Singleton
    public Gson getGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(RealmTranslateResponse.class, new TranslateMapper())
                .registerTypeAdapter(LanguageMap.class, new LanguageMapper())
                .create();
    }

    @Provides@Singleton
    public Retrofit.Builder getRetrofitBuilder(Converter.Factory converterFactory, OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    }




}
