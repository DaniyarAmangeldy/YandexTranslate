package daniyaramangeldy.yandextranslate.mvp.model.entity;



import io.realm.RealmObject;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class RealmLanguage extends RealmObject {
    private String name;
    private String id_;


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
}