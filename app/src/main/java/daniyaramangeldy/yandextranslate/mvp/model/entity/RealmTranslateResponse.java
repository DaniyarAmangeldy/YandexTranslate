package daniyaramangeldy.yandextranslate.mvp.model.entity;



import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Моделька для хранения Переводов в БД
 */

public class RealmTranslateResponse extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private RealmList<RealmString> text;
    private String originalText;
    private String lang;
    private boolean favourite;


    public RealmTranslateResponse() {
    }

    public RealmList<RealmString> getText() {
        return text;
    }

    public void setText(RealmList<RealmString> text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getId() {
        return id;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
