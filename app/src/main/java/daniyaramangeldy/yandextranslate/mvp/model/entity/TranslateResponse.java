package daniyaramangeldy.yandextranslate.mvp.model.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

@Table(name = "TranslateResponses")
public class TranslateResponse extends Model{
    @Column(name = "text")
    @Expose
    private ArrayList<String> text;
    @Column(name = "lang")
    @Expose
    private String lang;

    public TranslateResponse(ArrayList<String> text, String lang) {
        this.text = text;
        this.lang = lang;
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
}
