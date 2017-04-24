package daniyaramangeldy.yandextranslate.repository;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;


/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public interface Repository {

    /**
     * Получить список языков по API (ЕСЛИ языков с ui=lang нету в кэше)
     * @param lang
     * @return
     */
    Observable<LanguageMap> loadLanguages(String lang);

    /**
     * Функция Ищет Перевод в БД избранного и Истории
     * @param text
     * @param lang
     * @return
     */

    TranslateResponse findTranslateInCache(String text, String lang);

    /**
     * Переводит Текст . Запрос идет сначала в кэш , если в кэше нет, тогда по API
     * В Истории он записывается только если результат был получен по API
     * @param text
     * @param lang
     * @return
     */

    Observable<TranslateResponse> translateText(String text, String lang);

    /**
     * Получение списка истории из кэша
     * @return
     */

    List<TranslateResponse> getHistoryList();

    /**
     * Получение списка избранных из кэша
     * @return
     */

    List<Favourite> getFavourites();

    /**
     * Добавить Обьект(перевод) в избранное
     * @param response
     * @return
     */

    boolean addFavourite(TranslateResponse response);

    /**
     *  Удалить  Обьект(перевод) из избранного
     * @param id
     * @return
     */

    boolean removeFavourite(String id);

    /**
     * Очистить Историю
     * @return
     */

    boolean clearHistory();

    /**
     * Удалить запись из истории
     * @param text идет как id обьекта
     * @return
     */

    boolean removeHistory(String text);

    /**
     * Получить последний записанный результат из списка истории
     * @return
     */

    TranslateResponse getLastRequest();

    /**
     * Получение Обьекта избранное по original text(unique)
     * @param text
     * @return
     */

    Favourite getFavourite(String text);

    /**
     * Получение Обьекта перевода из истории по original text(unique)
     * @param text
     * @return
     */
    TranslateResponse getHistory(String text);

    /**
     * Получить список поддерживаемых языков
     * @param lang
     * @return
     */

    List<Language> getLanguageList(String lang);

    /**
     * Получить Name языка по его коду (ru -> Russian)
     * @param key
     * @param lang
     * @return
     */
    String getLangByKey(String key,String lang);
}
