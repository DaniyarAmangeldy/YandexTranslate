package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;


/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface MainView extends MvpView {
    void showMessage(int message);
    void initView();
}
