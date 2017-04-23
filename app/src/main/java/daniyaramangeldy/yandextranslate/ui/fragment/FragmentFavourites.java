package daniyaramangeldy.yandextranslate.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentHistoryBinding;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.presenter.FavouritePresenter;
import daniyaramangeldy.yandextranslate.mvp.view.FavouriteView;
import daniyaramangeldy.yandextranslate.ui.adapter.FavouriteAdapter;
import daniyaramangeldy.yandextranslate.ui.adapter.HistoryAdapter;

public class FragmentFavourites extends MvpAppCompatFragment implements FavouriteView, FragmentBookmark.favouriteUpdateListener {

    private FragmentHistoryBinding binding;
    private FavouriteAdapter adapter;

    @InjectPresenter
    FavouritePresenter presenter;

    @ProvidePresenter
    public FavouritePresenter providePresenter() {
        return new FavouritePresenter();
    }

    public FragmentFavourites() {
        // Required empty public constructor
    }


    public static FragmentFavourites newInstance() {
        FragmentFavourites fragment = new FragmentFavourites();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentBookmark parentFragment = ((FragmentBookmark) getParentFragment());
        parentFragment.setOnFavouriteUpdateListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_history, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        presenter.getFavourite();
    }

    @Override
    public void initRecyclerViewOrUpdate(List<Favourite> favourite) {
        if (adapter == null) {
            binding.bookmarksRv.setLayoutManager(getLayoutManager());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.bookmarksRv.getContext(),
                    ((LinearLayoutManager)binding.bookmarksRv.getLayoutManager()).getOrientation());
            binding.bookmarksRv.addItemDecoration(dividerItemDecoration);
            adapter = new FavouriteAdapter(favourite);
            binding.bookmarksRv.setAdapter(adapter);
        } else {
            adapter.setList(favourite);
        }
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate() {
        presenter.getFavourite();
    }
}
