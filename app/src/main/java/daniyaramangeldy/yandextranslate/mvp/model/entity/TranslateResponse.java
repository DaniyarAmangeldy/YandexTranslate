package daniyaramangeldy.yandextranslate.mvp.model.entity;

import java.util.ArrayList;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public class TranslateResponse {
    private String id;
    private ArrayList<String> text;
    private String lang;
    private boolean favourite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
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

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
