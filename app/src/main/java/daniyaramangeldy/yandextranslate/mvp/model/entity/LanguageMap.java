package daniyaramangeldy.yandextranslate.mvp.model.entity;

import android.support.v4.util.ArrayMap;

/**
 * Внимание Костыль , Моделька по сути обычная map. Нужна для маппера, Когда получаем язык ¯\_(ツ)_/¯
 */

public class LanguageMap {
    private ArrayMap<String,String> langMap;



    public LanguageMap() {
    }

    public ArrayMap<String, String> getLangMap() {
        return langMap;
    }

    public void setLangMap(ArrayMap<String, String> langMap) {
        this.langMap = langMap;
    }
}
