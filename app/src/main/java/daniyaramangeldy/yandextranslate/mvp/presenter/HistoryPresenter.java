package daniyaramangeldy.yandextranslate.mvp.presenter;

import android.content.res.Resources;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.mvp.view.HistoryView;


/**
 * Created by daniyaramangeldy on 22.04.17.
 */
@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    @Inject
    BookmarksInteractor bookmarksInteractor;
    @Inject
    Resources res;

    public HistoryPresenter(){
        MyApplication.component().inject(this);
    }


    public void getHistory() {
        getViewState().initRecyclerViewOrUpdate(bookmarksInteractor.getHistory());
    }



}
