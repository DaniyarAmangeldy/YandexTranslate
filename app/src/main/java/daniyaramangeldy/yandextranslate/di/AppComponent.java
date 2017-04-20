package daniyaramangeldy.yandextranslate.di;

import javax.inject.Singleton;

import dagger.Component;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentMain;

/**
 * Базовый компонент
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(FragmentMain fragment);

}
