package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentBookmarkBinding;
import daniyaramangeldy.yandextranslate.mvp.presenter.BookmarkPresenter;
import daniyaramangeldy.yandextranslate.mvp.view.BookmarkView;
import daniyaramangeldy.yandextranslate.ui.adapter.TabNavigationPagerAdapter;

import static android.app.Activity.RESULT_OK;


public class FragmentBookmark extends MvpAppCompatFragment implements ViewPager.OnPageChangeListener,BookmarkView {

    private FragmentBookmarkBinding binding;
    private TabNavigationPagerAdapter tabAdapter;
    private String nameArray[];
    private final int REQUEST_UPDATE_HISTORY = 2;
    private historyUpdateListener historyListener;
    private favouriteUpdateListener favouriteListener;
    private FloatingActionButton fabDelete;
    private Resources res;

    @InjectPresenter
    BookmarkPresenter presenter;

    @ProvidePresenter
    public BookmarkPresenter providePresenter() {
        return new BookmarkPresenter();
    }

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


    @Override
    public void showError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHistoryCleaned() {
        if(historyListener!=null){
            historyListener.onUpdate();
        }
    }


    interface historyUpdateListener {
        void onUpdate();
    }

    interface favouriteUpdateListener{
        void onUpdate();
    }


    public void setOnHistoryUpdateListener(historyUpdateListener listener) {
        this.historyListener = listener;
    }

    public void setOnFavouriteUpdateListener(favouriteUpdateListener listener) {
        this.favouriteListener = listener;
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
        res = getResources();
        nameArray = new String[]{res.getString(R.string.string_tab_history), res.getString(R.string.string_tab_favourite)};
        tabAdapter = new TabNavigationPagerAdapter(getChildFragmentManager(), nameArray);
        binding.fragmentBookmarkPager.setAdapter(tabAdapter);
        binding.fragmentBookmarkTablayout.setupWithViewPager(binding.fragmentBookmarkPager);
        binding.fragmentBookmarkPager.addOnPageChangeListener(this);
    }

    @OnClick(R.id.bookmarks_btn_delete)
    public void deleteHistory(){
        String message = res.getString(R.string.string_message_delete),
                ok = res.getString(R.string.string_ok),
                cancel = res.getString(R.string.string_cancel);
        new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(ok, (dialog, which) -> {
                    presenter.clearHistory();
                    dialog.dismiss();
                })
                .setNegativeButton(cancel,((dialogInterface, which) -> dialogInterface.dismiss()))
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_UPDATE_HISTORY) {
                if (historyListener != null && favouriteListener != null) {
                    historyListener.onUpdate();
                    favouriteListener.onUpdate();
                }
            }
        }
    }


}
