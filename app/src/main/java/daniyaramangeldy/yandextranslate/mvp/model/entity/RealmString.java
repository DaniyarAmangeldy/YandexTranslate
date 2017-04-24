package daniyaramangeldy.yandextranslate.mvp.model.entity;

import io.realm.RealmObject;

/**
 * Внимание Костыль. Я хотел подружиться с realm
 * ( Но он ударил меня по печени )
 * В общем RealmString нужен для хранения ArrayList<String> в БД(а arrayList<String> realm не научился хранить ).
 *  ¯\_(ツ)_/¯
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
