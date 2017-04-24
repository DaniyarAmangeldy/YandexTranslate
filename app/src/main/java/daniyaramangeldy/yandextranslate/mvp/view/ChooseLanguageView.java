package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;

/**
 * Вьюшка Окна Выбора языка
 */

public interface ChooseLanguageView extends MvpView {

    /**
     * recyclerView для списка языков
     * @param langList
     */
    void initRecyclerView(List<Language> langList);

}
