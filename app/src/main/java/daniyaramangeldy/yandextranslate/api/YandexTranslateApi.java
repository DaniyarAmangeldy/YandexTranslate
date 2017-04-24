package daniyaramangeldy.yandextranslate.api;

import android.support.v4.util.ArrayMap;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Мой Сервис для Retrofit
 */

public interface YandexTranslateApi {

    //TODO: Получить список поддержимаемых языков

    @POST("api/v1.5/tr.json/getLangs")
    Observable<LanguageMap> getLanguages(@Query("key") String key, @Query("ui") String ui);

    //TODO: Получить перевод текста

    @POST("api/v1.5/tr.json/translate")
    @FormUrlEncoded
    Observable<RealmTranslateResponse> getTranslate(@FieldMap ArrayMap<String,String> map);

}
