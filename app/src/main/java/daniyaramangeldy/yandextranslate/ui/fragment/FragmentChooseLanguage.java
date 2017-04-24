package daniyaramangeldy.yandextranslate.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import butterknife.OnClick;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentChooseLanguageBinding;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;
import daniyaramangeldy.yandextranslate.mvp.presenter.ChooseLanguagePresenter;
import daniyaramangeldy.yandextranslate.mvp.view.ChooseLanguageView;
import daniyaramangeldy.yandextranslate.ui.activity.ChooseLanguageActivity;
import daniyaramangeldy.yandextranslate.ui.adapter.ChooseLanguageAdapter;

public class FragmentChooseLanguage extends MvpAppCompatFragment implements ChooseLanguageView, ChooseLanguageAdapter.onClickListener {

    private FragmentChooseLanguageBinding binding;
    private String current;
    private int position;

    @InjectPresenter
    ChooseLanguagePresenter presenter;

    @ProvidePresenter
    public ChooseLanguagePresenter providePresenter(){
        return new ChooseLanguagePresenter();
    }

    public FragmentChooseLanguage() {
        // Required empty public constructor
    }

    public static FragmentChooseLanguage newInstance(String current,int position) {
        FragmentChooseLanguage fragment = new FragmentChooseLanguage();
        Bundle bundle = new Bundle();
        bundle.putString("current",current);
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            current = getArguments().getString("current");
            position = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_choose_language,container,false);
        View v = binding.getRoot();
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        presenter.getLanguage();
    }


    @OnClick(R.id.choose_language_back_button)
    public void back(){
        getActivity().onBackPressed();
    }


    public void initRecyclerView(List<Language> languageList){
        ChooseLanguageAdapter adapter = new ChooseLanguageAdapter(languageList,current);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.chooseLanguageRv.setLayoutManager(getLayoutManager());
        binding.chooseLanguageRv.setAdapter(adapter);
        binding.chooseLanguageRv.addItemDecoration(divider);
        adapter.setOnClickListener(this);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public void onClick(String key,String value) {
        Intent result = new Intent();
        result.putExtra("key",key);
        result.putExtra("value",value);
        result.putExtra("position",position);
        getActivity().setResult(Activity.RESULT_OK,result);
        getActivity().finish();
    }


}
