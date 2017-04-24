package daniyaramangeldy.yandextranslate.mvp.model.entity;

/**
 * Моделька для Избранных (не привязанная к realm) Нужна для слоя presenter и view
 */

public class Favourite {
    private String id;
    private String text;
    private String originalText;
    private String lang;
    private boolean favourite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isFavourite() {
        return favourite;
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
