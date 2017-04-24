package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;


/**
 * Вьюшка Истории
 */

public interface HistoryView extends MvpView {

    void initRecyclerViewOrUpdate(List<TranslateResponse> history);

    void showError(String s);

    void navigateToTranslate(String original,String translate,String lang);
}
