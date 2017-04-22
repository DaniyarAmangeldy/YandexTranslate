package daniyaramangeldy.yandextranslate.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;


/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public interface BookmarkView extends MvpView {

    void initRecyclerViewOrUpdate(List<TranslateResponse> history);
}
