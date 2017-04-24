package daniyaramangeldy.yandextranslate.mvp.model.entity;

import android.support.v4.util.ArrayMap;

/**
 * Created by daniyaramangeldy on 24.04.17.
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
