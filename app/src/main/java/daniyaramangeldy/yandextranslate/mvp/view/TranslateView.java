package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * View Для перевода
 */

public interface TranslateView extends MvpView {
    /**
     * Обработка нажатия смены языка
     */
    void swapLanguage();

    /**
     * Показать переведенный текст. Как только presenteru подьехал перевод
     * @param text
     */
    void showTranslateText(String text);



    void showError(String error);

    /**
     * Уведомить остальные фрагменты , что был добавлен новый обьект или удален из избранного
     */
    void eventBookmark();

    /**
     * При возобновлении и создании экрана,
     * устанавливает выбранные userom языки для перевода
     * @param from
     * @param to
     */

    void setCurrentLanguage(String from,String to);

    /**
     * Установка флага для избранного
     * @param check
     */

    void checkFavourite(boolean check);

    /**
     * Установить последний (сохранившийся в историй ) запрос.
     * Нужна, когда user выходит из приложения и возвращается
     * @param result
     */

    void setLastResult(String result);



    void showProgressBar();

    void hideProgressBar();
}
