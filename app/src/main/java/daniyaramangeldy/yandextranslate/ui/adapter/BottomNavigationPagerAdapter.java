package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import daniyaramangeldy.yandextranslate.ui.fragment.FragmentBookmark;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentTranslate;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class BottomNavigationPagerAdapter extends FragmentPagerAdapter {
    private final int FRAGMENT_COUNT = 2;
    private final int REQUEST_CODE = 1;
    Fragment fragmentTranslate;
    Fragment fragmentBookmark;

    public BottomNavigationPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentTranslate = FragmentTranslate.newInstance();
        fragmentBookmark = FragmentBookmark.newInstance();
        fragmentTranslate.setTargetFragment(fragmentBookmark,REQUEST_CODE);
        fragmentBookmark.setTargetFragment(fragmentTranslate,REQUEST_CODE);
    }

    @Override
    public Fragment getItem(int position) {
        return position==0 ?  fragmentTranslate : fragmentBookmark;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
