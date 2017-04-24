package daniyaramangeldy.yandextranslate.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentChooseLanguage;

public class ChooseLanguageActivity extends BaseActivity {

    @Override
    protected Fragment getFragment() {
        Intent intent = getIntent();
        String current = intent.getStringExtra("current");
        int position = intent.getIntExtra("position",0);
        return FragmentChooseLanguage.newInstance(current,position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_language;
    }

}
