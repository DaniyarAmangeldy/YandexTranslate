package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface TranslateView extends MvpView {
    void swapLanguage();
    void showTranslateText(String text);
    void showError(String error);
    void eventBookmark();
    void setCurrentLanguage(String from,String to);
    void checkFavourite(boolean check);
    void setLastResult(String result);
}
