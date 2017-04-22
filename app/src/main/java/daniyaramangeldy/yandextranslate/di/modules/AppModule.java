package daniyaramangeldy.yandextranslate.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
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

    @Provides@Singleton
    public SharedPreferences getSharedPreferences(){
        return application.getSharedPreferences("lang",Context.MODE_PRIVATE);
    }

    @Provides@Singleton
    public Context getContext(){
        return application;
    }

    @Provides@Singleton
    public Resources getResources(Context context){
        return context.getResources();
    }


}
