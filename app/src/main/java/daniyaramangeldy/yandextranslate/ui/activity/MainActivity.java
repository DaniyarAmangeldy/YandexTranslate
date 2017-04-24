package daniyaramangeldy.yandextranslate.ui.activity;

import android.support.v4.app.Fragment;

import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentMain;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected Fragment getFragment() {
        return FragmentMain.newInstance();
    }
}
