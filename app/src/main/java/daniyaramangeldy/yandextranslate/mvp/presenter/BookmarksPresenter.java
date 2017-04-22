package daniyaramangeldy.yandextranslate.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.mvp.view.BookmarkView;


/**
 * Created by daniyaramangeldy on 22.04.17.
 */
@InjectViewState
public class BookmarksPresenter extends MvpPresenter<BookmarkView> {

    @Inject
    BookmarksInteractor bookmarksInteractor;

    public BookmarksPresenter(){
        MyApplication.component().inject(this);
    }


    public void getHistory() {
        getViewState().initRecyclerViewOrUpdate(bookmarksInteractor.getHistory());
    }
}
