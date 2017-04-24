package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;

/**
 * Вьюшка Окна избранное
 */

public interface FavouriteView extends MvpView {

    /**
     * recyclerView для списка избранных
     * @param
     */
    void initRecyclerViewOrUpdate(List<Favourite> favourite);

    void showError(String s);

    /**
     * Функция Для обработки Нажатия на Историю или Избранное , переходит в окно перевода
     * @param original
     * @param translate
     * @param lang
     */

    void navigateToTranslate(String original,String translate,String lang);
}
