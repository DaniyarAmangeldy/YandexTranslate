package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by daniyaramangeldy on 23.04.17.
 */

public interface BookmarkView extends MvpView {
    void showError(String s);
    void onHistoryCleaned();

}
