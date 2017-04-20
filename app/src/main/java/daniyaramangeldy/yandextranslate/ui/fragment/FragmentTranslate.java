package daniyaramangeldy.yandextranslate.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentTranslateBinding;


public class FragmentTranslate extends MvpAppCompatFragment {

    private FragmentTranslateBinding binding;

    public FragmentTranslate() {
        // Required empty public constructor
    }


    public static FragmentTranslate newInstance() {
        FragmentTranslate fragment = new FragmentTranslate();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_translate, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this,view);
        return view;
    }

}
