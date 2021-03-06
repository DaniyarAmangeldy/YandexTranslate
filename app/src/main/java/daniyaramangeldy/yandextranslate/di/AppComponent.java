package daniyaramangeldy.yandextranslate.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import daniyaramangeldy.yandextranslate.di.modules.AppModule;
import daniyaramangeldy.yandextranslate.di.modules.BookmarksInteractorModule;
import daniyaramangeldy.yandextranslate.di.modules.TranslateInteractorModule;
import daniyaramangeldy.yandextranslate.mvp.presenter.BookmarkPresenter;
import daniyaramangeldy.yandextranslate.mvp.presenter.ChooseLanguagePresenter;
import daniyaramangeldy.yandextranslate.mvp.presenter.FavouritePresenter;
import daniyaramangeldy.yandextranslate.mvp.presenter.HistoryPresenter;
import daniyaramangeldy.yandextranslate.mvp.presenter.MainFragmentPresenter;
import daniyaramangeldy.yandextranslate.mvp.presenter.TranslatePresenter;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentBookmark;

/**
 * Базовый компонент
 */
@Singleton
@Component(modules = {AppModule.class, TranslateInteractorModule.class,BookmarksInteractorModule.class})
public interface AppComponent {
    Context getContext();
    


    void inject(MainFragmentPresenter presenter);
    void inject(TranslatePresenter presenter);
    void inject(FragmentBookmark fragment);
    void inject(HistoryPresenter presenter);
    void inject(FavouritePresenter presenter);
    void inject(BookmarkPresenter presenter);
    void inject(ChooseLanguagePresenter presenter);

}
