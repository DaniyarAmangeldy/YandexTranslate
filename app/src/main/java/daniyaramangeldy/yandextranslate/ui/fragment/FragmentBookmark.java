package daniyaramangeldy.yandextranslate.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentBookmarkBinding;


public class FragmentBookmark extends Fragment {

    private FragmentBookmarkBinding binding;

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
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bookmark, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this,view);
        return view;
    }

}
