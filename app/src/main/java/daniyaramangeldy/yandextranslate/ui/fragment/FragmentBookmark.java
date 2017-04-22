package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentBookmarkBinding;
import daniyaramangeldy.yandextranslate.ui.adapter.TabNavigationPagerAdapter;

import static android.app.Activity.RESULT_OK;


public class FragmentBookmark extends Fragment implements ViewPager.OnPageChangeListener {
    private static final String TAG = "FragmentBookmark";
    private FragmentBookmarkBinding binding;
    private TabNavigationPagerAdapter tabAdapter;
    private String nameArray[];
    private final int REQUEST_UPDATE_HISTORY = 2;
    private historyUpdateListener historyListener;
    private FloatingActionButton fabDelete;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}


    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                if (!fabDelete.isShown()) fabDelete.show();
                break;
            case 1:
                if (fabDelete.isShown()) fabDelete.hide();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public interface historyUpdateListener {
        void onUpdate();
    }

    public void setOnHistoryUpdateListener(historyUpdateListener listener) {
        this.historyListener = listener;
    }

    public FragmentBookmark() {
        // Required empty public constructor
    }


    public static FragmentBookmark newInstance() {
        FragmentBookmark fragment = new FragmentBookmark();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        fabDelete = binding.bookmarksBtnDelete;
        Resources res = getResources();
        nameArray = new String[]{res.getString(R.string.string_tab_history), res.getString(R.string.string_tab_favourite)};
        tabAdapter = new TabNavigationPagerAdapter(getChildFragmentManager(), nameArray);
        binding.fragmentBookmarkPager.setAdapter(tabAdapter);
        binding.fragmentBookmarkTablayout.setupWithViewPager(binding.fragmentBookmarkPager);
        binding.fragmentBookmarkPager.addOnPageChangeListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_UPDATE_HISTORY) {
                if (historyListener != null) {
                    historyListener.onUpdate();
                }
            }
        }
    }


}
