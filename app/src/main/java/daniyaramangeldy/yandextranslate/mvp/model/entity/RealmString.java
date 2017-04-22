package daniyaramangeldy.yandextranslate.mvp.model.entity;

import io.realm.RealmObject;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public class RealmString extends RealmObject {
    public String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public RealmString(String string) {
        this.string = string;
    }

    public RealmString(){

    }
}
