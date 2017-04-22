package daniyaramangeldy.yandextranslate.mvp.model.entity;


import java.util.Map;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class Language {
    private Map.Entry<String,String> name;
    private int id_;


    public Language() {
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }
}
