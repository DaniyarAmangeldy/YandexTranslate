package daniyaramangeldy.yandextranslate.mvp.model.entity;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by daniyaramangeldy on 23.04.17.
 */

public class RealmFavourite extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String text;
    private String originalText;
    private String lang;
    private boolean favourite;


    public RealmFavourite() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
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

