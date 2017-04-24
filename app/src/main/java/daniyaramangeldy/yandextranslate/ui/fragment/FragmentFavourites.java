package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

import static android.app.Activity.RESULT_OK;

public class FragmentFavourites extends MvpAppCompatFragment implements FavouriteView, FragmentBookmark.favouriteUpdateListener, FavouriteAdapter.onClickListener, SearchView.OnQueryTextListener {

    private final String EMPTY_MESSAGE = "";
    private final int REQUEST_UPDATE_HISTORY = 5;

    private FragmentHistoryBinding binding;
    private FavouriteAdapter adapter;
    private FragmentBookmark parentFragment;

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
        parentFragment = ((FragmentBookmark) getParentFragment());
        parentFragment.setOnFavouriteUpdateListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        presenter.getFavourite();
        setSearchView();
    }

    private void setSearchView() {
        binding.bookmarksSearchBar.setActivated(true);
        binding.bookmarksSearchBar.setQueryHint(getResources().getString(R.string.string_search_favourite));
        binding.bookmarksSearchBar.onActionViewExpanded();
        binding.bookmarksSearchBar.setIconified(false);
        binding.bookmarksSearchBar.clearFocus();
    }

    //TODO : Ставим или же обновляем список
    @Override
    public void initRecyclerViewOrUpdate(List<Favourite> favouriteList) {
        if (favouriteList.size() == 0) {
            setPlaceHolder(R.string.string_placeholder_favourites);
        } else {
            hidePlaceHolder();
        }
        if (adapter == null) {
            binding.bookmarksRv.setLayoutManager(getLayoutManager());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.bookmarksRv.getContext(),
                    ((LinearLayoutManager) binding.bookmarksRv.getLayoutManager()).getOrientation());
            binding.bookmarksRv.addItemDecoration(dividerItemDecoration);
            adapter = new FavouriteAdapter(favouriteList);
            adapter.setOnClickListener(this);
            binding.bookmarksRv.setAdapter(adapter);
            binding.bookmarksSearchBar.setOnQueryTextListener(this);
        } else {
            adapter.setList(favouriteList);
        }
    }

    private void hidePlaceHolder() {
        if (binding.bookmarksPlaceholder.getVisibility() == View.VISIBLE)
            binding.bookmarksPlaceholder.setVisibility(View.GONE);
    }
    // TODO: Placeholder для пустого массива
    private void setPlaceHolder(int placeHolder) {
        binding.bookmarksPlaceholder.setVisibility(View.VISIBLE);
        binding.bookmarksPlaceholder.setText(getResources().getString(placeHolder));
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    //TODO : При нажатии на элемент из списка -> переход в окно перевода
    @Override
    public void navigateToTranslate(String original, String translate, String lang) {
        parentFragment.navigateToTranslate(original, translate, true, lang);
    }

    //TODO: Обработка события , добавлено или изменено список избранных
    @Override
    public void onUpdate() {
        presenter.getFavourite();
    }

    @Override
    public void onClick(String text) {
        binding.bookmarksSearchBar.setQuery(EMPTY_MESSAGE,false);
        presenter.navigateToTranslate(text);
    }

    // TODO : Добавить или удалить выбранное из списка избранное
    @Override
    public void onCheckFavourite(Favourite favourite, boolean checked,int position) {
        boolean delete = presenter.removeFromFavourite(favourite);
        if(delete) {
            adapter.removeFavourite(position);
            Intent data = new Intent();
            data.putExtra("favourite",checked);
            data.putExtra("text",favourite.getOriginalText());
            parentFragment.onActivityResult(REQUEST_UPDATE_HISTORY,RESULT_OK,data);
        } else showError(getResources().getString(R.string.string_error_remove_favourite));

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filterText(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filterText(newText);
        return true;
    }
}
