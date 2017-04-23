package daniyaramangeldy.yandextranslate.mvp.presenter;

import android.content.res.Resources;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.view.FavouriteView;

/**
 * Created by daniyaramangeldy on 23.04.17.
 */

@InjectViewState
public class FavouritePresenter extends MvpPresenter<FavouriteView> {

    @Inject
    BookmarksInteractor bookmarksInteractor;
    @Inject
    Resources res;

    public FavouritePresenter(){
        MyApplication.component().inject(this);
    }

    public void getFavourite() {
        getViewState().initRecyclerViewOrUpdate(bookmarksInteractor.getFavourites());
    }

    public void navigateToTranslate(String text){
        Favourite favourite = bookmarksInteractor.getFavourite(text);
        if(favourite!=null){
            getViewState().navigateToTranslate(favourite.getOriginalText(),favourite.getText(),favourite.getLang());
        }
    }

}
