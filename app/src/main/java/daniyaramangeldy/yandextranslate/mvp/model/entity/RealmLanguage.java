package daniyaramangeldy.yandextranslate.mvp.model.entity;



import io.realm.RealmObject;

/**
 * Моделька для Языка (Привязанная realm).она хранится в БД
 */

public class RealmLanguage extends RealmObject {
    private String name;
    private String id_;
    private String lang;


    public RealmLanguage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
