package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;

/**
 * Created by daniyaramangeldy on 24.04.17.
 */

public interface ChooseLanguageView extends MvpView {

    void initRecyclerView(List<Language> langList);

}
