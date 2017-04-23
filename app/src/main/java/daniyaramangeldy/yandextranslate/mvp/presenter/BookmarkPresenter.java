package daniyaramangeldy.yandextranslate.mvp.presenter;

import android.content.res.Resources;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.mvp.view.BookmarkView;

/**
 * Created by daniyaramangeldy on 23.04.17.
 */

@InjectViewState
public class BookmarkPresenter extends MvpPresenter<BookmarkView> {

    @Inject
    BookmarksInteractor bookmarksInteractor;
    @Inject
    Resources res;

    public BookmarkPresenter(){
        MyApplication.component().inject(this);
    }

    public void clearHistory() {
        boolean result = bookmarksInteractor.clearHistory();
        if(result){
            getViewState().onHistoryCleaned();
        }else{
            String s = res.getString(R.string.string_error_clear_history);
            getViewState().showError(s);
        }
    }
}
