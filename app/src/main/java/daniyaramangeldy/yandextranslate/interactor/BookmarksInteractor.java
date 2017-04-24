package daniyaramangeldy.yandextranslate.interactor;

import java.util.List;

import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Интерактор (UseCase) Для Закладок (История, Избранные)
 */

public interface BookmarksInteractor {

    /**
     * Вызывается каждый раз когда добавляется новый перевод (не из кэша)
     * @return Получение списка истории
     */
    List<TranslateResponse> getHistoryList();

    /**
     * Очистка истории , по нажатии на FAB.
     * @return Очистка успешно завершена
     */
    boolean clearHistory();

    /**
     * Добавляет в избранное перевод
     * @param response Обьект Перевода ( текст , язык )
     * @return Успешно - не успешно
     */

    boolean addFavourite(TranslateResponse response);

    /**
     * Удаление обьекта  из избранного
     * @param name оригинальный текст , принимается как unique
     * @return Успешно - не успешно
     */

    boolean removeFavourite(String name);

    /**
     * Удаление определенный обьект response из истории
     * @param text
     * @return Успешно - не успешно
     */

    boolean removeHistory(String text);

    /**
     *  Получаем список избранных, для заполнения recyclerView
     * @return список избранных
     */

    List<Favourite> getFavourites();

    /**
     *
     * @return получает последний запрос , которую смотрел user.
     */

    TranslateResponse getLastRequest();

    /**
     *
     * @param text оригинальный текст , используется как unique
     * @return Определенный обьект favourite
     */

    Favourite getFavourite(String text);

    /**
     *
     * @param text оригинальный текст , используется как unique
     * @return Определенный обьект favourite
     */

    TranslateResponse getHistory(String text);
}
