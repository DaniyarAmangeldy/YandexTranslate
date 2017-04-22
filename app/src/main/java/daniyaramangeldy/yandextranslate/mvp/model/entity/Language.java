package daniyaramangeldy.yandextranslate.mvp.model.entity;

import android.support.v4.util.ArrayMap;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

@Table(name = "Languages")
public class Language extends Model {

    @Column(name = "name")
    private Map.Entry<String,String> name;

    @Column(name = "id_", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private int id_;


    public Language(Map.Entry<String, String> name, int id_) {
        this.name = name;
        this.id_ = id_;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }
}
