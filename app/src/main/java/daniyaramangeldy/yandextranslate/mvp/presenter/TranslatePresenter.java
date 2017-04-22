package daniyaramangeldy.yandextranslate.mvp.presenter;


import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.TranslateInteractor;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.view.TranslateView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */
@InjectViewState
public class TranslatePresenter extends MvpPresenter<TranslateView> {

    @Inject
    TranslateInteractor interactor;

    public TranslatePresenter(){
        MyApplication.component().inject(this);
     }

     public void inputVoice(){
        getViewState().showMessage(R.string.string_lang_english);
     }

    public void inputMic(){
        getViewState().showMessage(R.string.string_lang_russian);
    }

    public void outputVoice(){
        getViewState().showMessage(R.string.string_lang_short_english);
    }

    public void addBookmark(){
        getViewState().showMessage(R.string.string_lang_short_russian);
    }

    public void swapLanguage(){
        getViewState().swapLanguage();
    }

    public void translateText(String text){
        if(!TextUtils.isEmpty(text)){
            interactor.translateText(text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new resultObserver());
        }

    }

    private class resultObserver implements Observer<TranslateResponse> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(TranslateResponse value) {
            getViewState().showTranslateText(value.getText().get(0));
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
