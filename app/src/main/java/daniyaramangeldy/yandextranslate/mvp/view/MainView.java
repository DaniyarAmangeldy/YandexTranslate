package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;


/**
 * Основная Вьюшка
 */

public interface MainView extends MvpView {
    void initView();

    /**
     * Функция для перехода в окно Перевода (Нужна для child фрагментов)
     */
    void navigateToTranslateWindow();
}
