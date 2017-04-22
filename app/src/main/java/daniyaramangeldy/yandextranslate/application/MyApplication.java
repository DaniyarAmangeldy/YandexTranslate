package daniyaramangeldy.yandextranslate.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import daniyaramangeldy.yandextranslate.di.AppComponent;
import daniyaramangeldy.yandextranslate.di.modules.AppModule;
import daniyaramangeldy.yandextranslate.di.DaggerAppComponent;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class MyApplication extends Application {

    private static AppComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }


        LeakCanary.install(this);

        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent component() {
        return component;
    }


}
