package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import daniyaramangeldy.yandextranslate.ui.fragment.FragmentBookmark;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentTranslate;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

public class NavigationPagerAdapter extends FragmentPagerAdapter {
    private final int FRAGMENT_COUNT = 2;

    public NavigationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return position==0 ? FragmentTranslate.newInstance() : FragmentBookmark.newInstance();
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
