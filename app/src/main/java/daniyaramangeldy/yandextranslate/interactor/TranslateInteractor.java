package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.model.entity.LanguageMap;
import daniyaramangeldy.yandextranslate.mvp.model.entity.RealmTranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import io.reactivex.Observable;

/**
 * Интерактор ( usecase ) для Переводов, языков
 */

public interface TranslateInteractor {


    /**
     * Получение Перевода
     * @param text
     * @return обьект TranslateResponse с переводом текста
     */
    Observable<TranslateResponse> translateText(String text);


    /**
     *
     * @return Возвращает опреденный язык по его коду (ru -> Russian)
     */

    String[] getCurrentLanguageKey();

    /**
     *
     * Возвращает Язык , которую установил user, или default
     */

    String[] getCurrentLanguage();

    /**
     * Поменять языки местами,
     */

    void swapLanguage();

    /**
     *
     * @param lang системный язык
     * @return Сохранить в кэш системный язык
     */

    boolean setLanguage(String lang);

    /**
     * Получить(закешировать ) список поддерживаемых языков
     * @return
     */

    Observable<LanguageMap> loadLanguages();

    /**
     * Возвращает ранее закешированный список языков
     * @return
     */

    List<Language> getLanguageList();

}
