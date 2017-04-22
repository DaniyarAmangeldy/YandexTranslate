package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentMainBinding;
import daniyaramangeldy.yandextranslate.mvp.presenter.MainFragmentPresenter;
import daniyaramangeldy.yandextranslate.ui.adapter.BottomNavigationPagerAdapter;
import daniyaramangeldy.yandextranslate.mvp.view.MainView;


public class FragmentMain extends MvpAppCompatFragment implements MainView, AHBottomNavigation.OnTabSelectedListener {

    private Resources res;

    private FragmentMainBinding binding;
    private BottomNavigationPagerAdapter adapter;
    private ViewPager pager;

    public FragmentMain() {
        // Required empty public constructor
    }

    MainFragmentPresenter providePresenter(){
        return new MainFragmentPresenter();
    }

    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this,view);
        initView();
        return view;
    }


    @Override
    public void initView() {
        res = getResources();
        pager = binding.fragmentMainContainer;
        AHBottomNavigation navigation = binding.fragmentMainNavigation;

        AHBottomNavigationItem itemTranslate = new AHBottomNavigationItem(
                res.getString(R.string.string_navigation_translate),ContextCompat.getDrawable(getContext(),R.drawable.ic_translate_black_36dp),ContextCompat.getColor(getContext(),R.color.colorPrimary));
        AHBottomNavigationItem itemBookmark = new AHBottomNavigationItem(
                res.getString(R.string.string_navigation_tab), ContextCompat.getDrawable(getContext(),R.drawable.ic_bookmark_black_36dp),ContextCompat.getColor(getContext(),R.color.colorAccent));

        navigation.addItem(itemTranslate);
        navigation.addItem(itemBookmark);

        navigation.setInactiveColor(android.R.color.darker_gray);

        navigation.setOnTabSelectedListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            navigation.setRevealOnFocusHint(true);
        }
        navigation.setColored(true);

        adapter = new BottomNavigationPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        pager.setCurrentItem(position,false);
        return true;
    }
}
