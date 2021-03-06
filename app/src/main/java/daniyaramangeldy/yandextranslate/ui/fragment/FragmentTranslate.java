package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentTranslateBinding;
import daniyaramangeldy.yandextranslate.mvp.presenter.TranslatePresenter;
import daniyaramangeldy.yandextranslate.mvp.view.TranslateView;
import daniyaramangeldy.yandextranslate.ui.activity.ChooseLanguageActivity;

import static android.app.Activity.RESULT_OK;


public class FragmentTranslate extends MvpAppCompatFragment implements TranslateView, TextWatcher {
    private static final String TAG = "FragmentTranslate";
    private final String EMPTY_MESSAGE = "";
    private final int REQUEST_UPDATE_HISTORY = 2;
    private final int REQUEST_NAVIGATE = 3;
    private static final int REQUEST_CHANGE_LANGUAGE = 9;
    private static final String EXTRA_TEXT_ORIGINAL = "original";
    private static final String EXTRA_LANGUAGE = "language";

    private boolean favouriteListenerOn = true;

    @InjectPresenter
    TranslatePresenter presenter;

    Resources res;
    private FragmentTranslateBinding binding;
    private Timer timer;
    private String lastWord;
    private InputMethodManager imm;

    public FragmentTranslate() {
        // Required empty public constructor
    }


    @ProvidePresenter
    TranslatePresenter provideRepositoryPresenter() {
        return new TranslatePresenter();
    }

    public static FragmentTranslate newInstance() {
        FragmentTranslate fragment = new FragmentTranslate();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            lastWord = savedInstanceState.getString("input");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_translate, container, false);
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
        res = getResources();
        presenter.initLastTranslate();
        presenter.loadLanguages();
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        binding.fragmentTranslateOutput.setMovementMethod(new ScrollingMovementMethod());
        setLastWord();
        binding.fragmentTranslateInput.addTextChangedListener(this);
        setLinkLabel();
    }

    private void setLastWord() {
        if (lastWord != null) binding.fragmentTranslateInput.setText(lastWord);
    }

    private void setLinkLabel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.yandexLinkBtn.setText(Html.fromHtml(
                    "<a href=\"http://translate.yandex.ru/\">" + getResources().getString(R.string.string_label_link) + "</a>", Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.yandexLinkBtn.setText(Html.fromHtml(
                    "<a href=\"http://translate.yandex.ru/\">" + getResources().getString(R.string.string_label_link) + "</a>"));
        }
        binding.yandexLinkBtn.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @OnClick(R.id.fragment_translate_btn_clear)
    public void clearInputClick() {
        binding.fragmentTranslateInput.setText(EMPTY_MESSAGE);
        binding.fragmentTranslateOutput.setText(EMPTY_MESSAGE);
        focusInput();
    }


    @OnClick(R.id.fragment_translate_swap)
    public void swapLanguageClick() {
        presenter.swapLanguage();
    }

    @OnClick(R.id.fragment_translate_layout_whiteboard)
    public void whiteboardClick() {
        focusInput();
    }

    @OnClick(R.id.fragment_translate_lang_1)
    public void changeLangFrom() {
        Intent intent = new Intent(getActivity(), ChooseLanguageActivity.class);
        intent.putExtra("current", presenter.getCurrentLanguage(0));
        intent.putExtra("position", 0);
        startActivityForResult(intent, REQUEST_CHANGE_LANGUAGE);
    }

    @OnClick(R.id.fragment_translate_lang_2)
    public void changeLangTo() {
        Intent intent = new Intent(getActivity(), ChooseLanguageActivity.class);
        intent.putExtra("current", presenter.getCurrentLanguage(0));
        intent.putExtra("position", 1);
        startActivityForResult(intent, REQUEST_CHANGE_LANGUAGE);
    }

    @OnCheckedChanged(R.id.fragment_translate_output_btn_bookmark)
    public void FavouriteCheck(boolean isChecked) {
        if (favouriteListenerOn) {
            if (!isChecked) {
                presenter.removeFavourite();
            } else {
                presenter.addFavourite();
            }
        }
    }

    @Override
    public void checkFavourite(boolean check) {
        favouriteListenerOn = false;
        binding.fragmentTranslateOutputBtnBookmark.setChecked(check);
        favouriteListenerOn = true;
    }

    @Override
    public void setLastResult(String result) {
        binding.fragmentTranslateInput.setText(result);
    }

    @Override
    public void showProgressBar() {
        getActivity().runOnUiThread(() -> {
            if (binding.fragmentTranslateOutput.isShown() && binding.fragmentTranslateInput.length() > 0) {
                binding.fragmentTranslateOutput.setVisibility(View.GONE);
                binding.fragmentTransaltePb.setVisibility(View.VISIBLE);
                binding.fragmentTranslateSwap.setEnabled(false);
            }
        });

    }

    @Override
    public void hideProgressBar() {
        if (binding.fragmentTransaltePb.isShown()) {
            binding.fragmentTransaltePb.setVisibility(View.GONE);
            binding.fragmentTranslateOutput.setVisibility(View.VISIBLE);
            binding.fragmentTranslateSwap.setEnabled(true);
        }
    }


    private void focusInput() {

        imm.showSoftInput(binding.fragmentTranslateInput, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void swapLanguage() {
        String temp = binding.fragmentTranslateLang1.getText().toString();
        binding.fragmentTranslateLang1.setText(binding.fragmentTranslateLang2.getText().toString());
        binding.fragmentTranslateLang2.setText(temp);
        if (!TextUtils.isEmpty(binding.fragmentTranslateInput.getText().toString())) {
            binding.fragmentTranslateInput.setText(binding.fragmentTranslateOutput.getText().toString());
            int position = binding.fragmentTranslateInput.length();
            binding.fragmentTranslateInput.setSelection(position);
        }
    }

    @Override
    public void showTranslateText(String text) {
        binding.fragmentTranslateOutput.setText(text);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eventBookmark() {
        getTargetFragment().onActivityResult(REQUEST_UPDATE_HISTORY, RESULT_OK, null);
    }

    @Override
    public void setCurrentLanguage(String from, String to) {
        binding.fragmentTranslateLang1.setText(from);
        binding.fragmentTranslateLang2.setText(to);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            binding.fragmentTranslateBtnClear.setVisibility(View.VISIBLE);
            binding.fragmentTranslateOutputBtnBookmark.setVisibility(View.VISIBLE);
        } else {
            binding.fragmentTranslateOutput.setText(EMPTY_MESSAGE);
            binding.fragmentTranslateBtnClear.setVisibility(View.INVISIBLE);
            binding.fragmentTranslateOutputBtnBookmark.setVisibility(View.INVISIBLE);
        }
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TranslateTask(s.toString()), 1000);

    }


    private class TranslateTask extends TimerTask {
        String text;

        public TranslateTask(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            presenter.translateText(text);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(binding.fragmentTranslateInput.getText().toString())) {
            outState.putString("input", binding.fragmentTranslateInput.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_NAVIGATE:
                    if (data != null) {
                        lastWord = data.getStringExtra(EXTRA_TEXT_ORIGINAL);
                        String language = data.getStringExtra(EXTRA_LANGUAGE);
                        binding.fragmentTranslateInput.setText(lastWord);
                        presenter.setCurrentLanguage(language);
                    }
                    break;
                case REQUEST_UPDATE_HISTORY:
                    if (data != null) {
                        if (binding.fragmentTranslateInput.getText().toString().equals(data.getStringExtra("text"))) {
                            favouriteListenerOn = !favouriteListenerOn;
                            binding.fragmentTranslateOutputBtnBookmark.setChecked(data.getBooleanExtra("favourite", false));
                            favouriteListenerOn = !favouriteListenerOn;

                        }
                    }
                    break;
                case REQUEST_CHANGE_LANGUAGE:
                    if(data!=null){
                        int position = data.getIntExtra("position",0);
                        String currentLanguage = presenter.getCurrentLanguage(position);
                        if(position == 0) presenter.setCurrentLanguage(String.format("%s-%s",data.getStringExtra("key"),currentLanguage));
                        else presenter.setCurrentLanguage(String.format("%s-%s",currentLanguage,data.getStringExtra("key")));
                    }
                    break;

            }

        }

    }
}
