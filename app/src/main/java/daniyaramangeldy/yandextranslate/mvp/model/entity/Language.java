package daniyaramangeldy.yandextranslate.mvp.model.entity;

/**
 * Моделька для Языка (не привязанная к realm) Нужна для слоя presenter и view
 */

public class Language  {
    private String name;
    private String id_;


    public Language() {
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
