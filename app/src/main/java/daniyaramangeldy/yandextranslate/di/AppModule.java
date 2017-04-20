package daniyaramangeldy.yandextranslate.di;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import daniyaramangeldy.yandextranslate.application.MyApplication;

/**
 * Базовый модуль
 */
@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application){
        this.application = application;
    }

    @Provides @Singleton
    public Context getContext(){
        return application;
    }

    @Provides @Singleton
    public Resources getResources(){
        return application.getResources();
    }



}
