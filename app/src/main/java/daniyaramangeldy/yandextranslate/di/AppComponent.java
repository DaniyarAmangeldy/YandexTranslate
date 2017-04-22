package daniyaramangeldy.yandextranslate.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import daniyaramangeldy.yandextranslate.di.modules.AppModule;
import daniyaramangeldy.yandextranslate.di.modules.TranslateInteractorModule;
import daniyaramangeldy.yandextranslate.interactor.TranslateInteractor;
import daniyaramangeldy.yandextranslate.mvp.presenter.MainFragmentPresenter;
import daniyaramangeldy.yandextranslate.mvp.presenter.TranslatePresenter;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentBookmark;

/**
 * Базовый компонент
 */
@Singleton
@Component(modules = {AppModule.class, TranslateInteractorModule.class})
public interface AppComponent {
    Context getContext();
    TranslateInteractor getTranslateInteractor();


    void inject(MainFragmentPresenter presenter);
    void inject(TranslatePresenter presenter);
    void inject(FragmentBookmark fragment);

}
