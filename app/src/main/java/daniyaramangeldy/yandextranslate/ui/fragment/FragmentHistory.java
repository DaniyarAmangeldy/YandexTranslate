package daniyaramangeldy.yandextranslate.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;
import daniyaramangeldy.yandextranslate.mvp.presenter.HistoryPresenter;
import daniyaramangeldy.yandextranslate.mvp.view.HistoryView;
import daniyaramangeldy.yandextranslate.ui.adapter.HistoryAdapter;


public class FragmentHistory extends MvpAppCompatFragment implements HistoryView, FragmentBookmark.historyUpdateListener, HistoryAdapter.onClickListener {

    private FragmentHistoryBinding binding;
    private HistoryAdapter adapter;
    private FragmentBookmark parentFragment;

    @InjectPresenter
    HistoryPresenter presenter;

    public FragmentHistory() {
        // Required empty public constructor
    }

    @ProvidePresenter
    public HistoryPresenter providePresenter() {
        return new HistoryPresenter();
    }

    public static FragmentHistory newInstance() {
        return new FragmentHistory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentFragment = ((FragmentBookmark) getParentFragment());
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
        if(history.size()==0) parentFragment.hideFabButton();
        else parentFragment.showFabButton();
        if (adapter == null) {
            binding.bookmarksRv.setLayoutManager(getLayoutManager());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.bookmarksRv.getContext(),
                    ((LinearLayoutManager)binding.bookmarksRv.getLayoutManager()).getOrientation());
            binding.bookmarksRv.addItemDecoration(dividerItemDecoration);
            adapter = new HistoryAdapter(history);
            adapter.setOnClickListener(this);
            binding.bookmarksRv.setAdapter(adapter);
        } else {
            adapter.setList(history);
        }
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToTranslate(String original, String translate, String lang) {
        parentFragment.navigateToTranslate(original,translate,true,lang);
    }

    @Override
    public void onUpdate() {
        presenter.getHistory();
    }

    @Override
    public void onClick(String text) {
        presenter.navigateToTranslate(text);
    }

    @Override
    public void onLongClick(View v, String text,int position) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(),v, Gravity.CENTER_HORIZONTAL);
        popupMenu.inflate(R.menu.delete_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            boolean deleted = presenter.removeFromHistory(text);
            if(deleted) adapter.deleteItem(position);

            return false;
        });
        popupMenu.show();
    }
}
