package daniyaramangeldy.yandextranslate.mvp.model.entity;



import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class RealmTranslateResponse extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private RealmList<RealmString> text;
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

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}