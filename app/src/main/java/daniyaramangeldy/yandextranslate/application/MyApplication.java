package daniyaramangeldy.yandextranslate.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import daniyaramangeldy.yandextranslate.di.AppComponent;
import daniyaramangeldy.yandextranslate.di.AppModule;
import daniyaramangeldy.yandextranslate.di.DaggerAppComponent;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class MyApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent component() {
        return component;


    }
}
