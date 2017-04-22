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

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentHistoryBinding;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.presenter.BookmarksPresenter;
import daniyaramangeldy.yandextranslate.mvp.view.BookmarkView;
import daniyaramangeldy.yandextranslate.ui.adapter.BookmarkAdapter;



public class FragmentHistory extends MvpAppCompatFragment implements BookmarkView, FragmentBookmark.historyUpdateListener {

    private FragmentHistoryBinding binding;
    private BookmarkAdapter adapter;

    @InjectPresenter
    BookmarksPresenter presenter;

    public FragmentHistory() {
        // Required empty public constructor
    }

    @ProvidePresenter
    public BookmarksPresenter providePresenter() {
        return new BookmarksPresenter();
    }

    public static FragmentHistory newInstance() {
        return new FragmentHistory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentBookmark parentFragment = ((FragmentBookmark) getParentFragment());
        parentFragment.setOnHistoryUpdateListener(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
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
        presenter.getHistory();

    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }



    @Override
    public void initRecyclerViewOrUpdate(List<TranslateResponse> history) {
        if (adapter == null) {
            binding.bookmarksRv.setLayoutManager(getLayoutManager());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.bookmarksRv.getContext(),
                    ((LinearLayoutManager)binding.bookmarksRv.getLayoutManager()).getOrientation());
            binding.bookmarksRv.addItemDecoration(dividerItemDecoration);
            adapter = new BookmarkAdapter(history);
            binding.bookmarksRv.setAdapter(adapter);
        } else {
            adapter.setList(history);
        }
    }

    @Override
    public void onUpdate() {
        presenter.getHistory();
    }
}
