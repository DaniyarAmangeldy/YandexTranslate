package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;

/**
 * Created by daniyaramangeldy on 23.04.17.
 */

public interface FavouriteView extends MvpView {

    void initRecyclerViewOrUpdate(List<Favourite> favourite);

    void showError(String s);

    void navigateToTranslate(String original,String translate,String lang);
}
