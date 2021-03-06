package daniyaramangeldy.yandextranslate.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;

import daniyaramangeldy.yandextranslate.R;

/**
 * Базовое Активити. Abstract метод атачит Фрагменту , которую я хочу
 */

public abstract class BaseActivity extends MvpAppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected abstract Fragment getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_container);

        if (fragment == null) {
            fragment = getFragment();
            fm.beginTransaction()
                    .add(R.id.activity_container, fragment)
                    .commit();
        }
    }

    @LayoutRes
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
