package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Вьюшка для parent фрагмента History и Translate
 */

public interface BookmarkView extends MvpView {
    void showError(String s);

    /**
     * Слушаетль , когда произошла очистка истории
     */
    void onHistoryCleaned();

    /**
     * Функция Для обработки Нажатия на Историю или Избранное , переходит в окно перевода
     * @param original
     * @param translate
     * @param favourite
     * @param lang
     */
    void navigateToTranslate(String original,String translate,boolean favourite,String lang);

}
