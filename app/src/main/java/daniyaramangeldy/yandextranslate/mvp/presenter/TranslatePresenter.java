package daniyaramangeldy.yandextranslate.mvp.presenter;


import android.content.res.Resources;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.BookmarksInteractor;
import daniyaramangeldy.yandextranslate.interactor.TranslateInteractor;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.view.TranslateView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */
@InjectViewState
public class TranslatePresenter extends MvpPresenter<TranslateView> {

    private static final String TAG = "TranslatePresenter";
    private TranslateResponse lastResponse;



    @Inject
    TranslateInteractor translateInteractor;

    @Inject
    BookmarksInteractor bookmarksInteractor;

    @Inject
    Resources res;

    public TranslatePresenter() {
        MyApplication.component().inject(this);
    }

    public void addFavourite() {
        boolean result = bookmarksInteractor.addFavourite(lastResponse);
        if(result){
            getViewState().eventBookmark();

        } else {
            getViewState().showError(res.getString(R.string.string_error_add_favourite));
            getViewState().checkFavourite(false);

        }
    }

    public void removeFavourite(){
        boolean result = bookmarksInteractor.removeFavourite(lastResponse.getOriginalText());
        if(result) {
            getViewState().eventBookmark();
        } else {
            getViewState().showError(res.getString(R.string.string_error_remove_favourite));
            getViewState().checkFavourite(true);
        }
    }

    public void swapLanguage() {
        translateInteractor.swapLanguage();
        getViewState().swapLanguage();
    }

    public void translateText(String text) {
        if (!TextUtils.isEmpty(text)) {
            translateInteractor.translateText(text.trim())
                    .subscribe(new resultObserver());
        }
    }

    public void initCurrentLanguage() {
        String[] langs = translateInteractor.getCurrentLanguage();
        getViewState().setCurrentLanguage(langs[0], langs[1]);
    }

    public void initLastTranslate(){
        TranslateResponse response = bookmarksInteractor.getLastRequest();
        if(response!=null) getViewState().setLastResult(response.getOriginalText());
    }


    private class resultObserver implements Observer<TranslateResponse> {

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(TranslateResponse value) {
            lastResponse = value;
            getViewState().checkFavourite(value.isFavourite());
            getViewState().eventBookmark();
            getViewState()
                    .showTranslateText(value.getText());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            getViewState().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }

}
