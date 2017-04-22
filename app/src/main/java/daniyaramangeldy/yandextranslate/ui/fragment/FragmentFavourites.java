package daniyaramangeldy.yandextranslate.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentHistoryBinding;

public class FragmentFavourites extends Fragment {

    private FragmentHistoryBinding binding;

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

}
