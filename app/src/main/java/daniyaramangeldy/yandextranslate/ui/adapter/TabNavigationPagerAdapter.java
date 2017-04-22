package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import daniyaramangeldy.yandextranslate.ui.fragment.FragmentFavourites;
import daniyaramangeldy.yandextranslate.ui.fragment.FragmentHistory;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public class TabNavigationPagerAdapter extends FragmentPagerAdapter {

    private String[] nameArray;
    private final int TAB_COUNT = 2;

    public TabNavigationPagerAdapter(FragmentManager fm,String[] nameArray) {
        super(fm);
        this.nameArray = nameArray;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = FragmentHistory.newInstance();
                break;
            case 1:
                fragment = FragmentFavourites.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nameArray[position];
    }
}
