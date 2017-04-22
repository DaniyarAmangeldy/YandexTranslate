package daniyaramangeldy.yandextranslate.api;

import android.support.v4.util.ArrayMap;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface YandexTranslateApi {

    @POST("api/v1.5/tr.json/getLangs")
    Observable<List<Language>> getLanguages(@Query("key") String key, @Query("ui") String ui);

    @POST("api/v1.5/tr.json/translate")
    @FormUrlEncoded
    Observable<TranslateResponse> getTranslate(@FieldMap ArrayMap<String,String> map);

}