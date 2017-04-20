package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import javax.inject.Inject;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.application.MyApplication;
import daniyaramangeldy.yandextranslate.databinding.FragmentMainBinding;
import daniyaramangeldy.yandextranslate.ui.adapter.NavigationPagerAdapter;
import daniyaramangeldy.yandextranslate.view.MainView;


public class FragmentMain extends MvpAppCompatFragment implements MainView, AHBottomNavigation.OnTabSelectedListener {
    @Inject
    Resources res;

    private FragmentMainBinding binding;
    private NavigationPagerAdapter adapter;
    private ViewPager pager;

    public FragmentMain() {
        // Required empty public constructor
    }


    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getActivity().getApplication()).component().inject(this);
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
    public void showMessage(int message) {
    }

    @Override
    public void initView() {
        pager = binding.fragmentMainContainer;
        AHBottomNavigation navigation = binding.fragmentMainNavigation;

        AHBottomNavigationItem itemTranslate = new AHBottomNavigationItem(
                res.getString(R.string.string_navigation_translate),ContextCompat.getDrawable(getContext(),R.drawable.ic_translate_black_36dp),ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
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

        adapter = new NavigationPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        pager.setCurrentItem(position,false);
        return true;
    }
}
