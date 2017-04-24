package daniyaramangeldy.yandextranslate.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.interactor.TranslateInteractor;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.view.ChooseLanguageView;

/**
 * Created by daniyaramangeldy on 24.04.17.
 */

@InjectViewState
public class ChooseLanguagePresenter extends MvpPresenter<ChooseLanguageView> {
    @Inject
    TranslateInteractor interactor;

    public ChooseLanguagePresenter(){
        MyApplication.component().inject(this);
    }

    public void getLanguage(){
        List<Language> languageList = interactor.getLanguageList();
        if(languageList!=null && languageList.size()!=0){
            getViewState().initRecyclerView(languageList);
        }
    }


}
