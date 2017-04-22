package daniyaramangeldy.yandextranslate.ui.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.databinding.FragmentTranslateBinding;
import daniyaramangeldy.yandextranslate.mvp.presenter.TranslatePresenter;
import daniyaramangeldy.yandextranslate.mvp.view.TranslateView;
import daniyaramangeldy.yandextranslate.utils.Utils;


public class FragmentTranslate extends MvpAppCompatFragment implements TranslateView, TextWatcher {
    private final String EMPTY_MESSAGE = "";

    @InjectPresenter
    TranslatePresenter presenter;
    Resources res;
    private FragmentTranslateBinding binding;
    private Timer timer;
    private String lastWord;

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
        if(savedInstanceState!=null){
            String lastWord = savedInstanceState.getString("input");
        }
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        res = getResources();
        binding.fragmentTranslateInput.setMovementMethod(new ScrollingMovementMethod());
        binding.fragmentTranslateLang1.setText(res.getString(R.string.string_lang_russian));
        binding.fragmentTranslateLang2.setText(res.getString(R.string.string_lang_english));
        if(lastWord!=null) binding.fragmentTranslateInput.setText(lastWord);
        binding.fragmentTranslateInput.addTextChangedListener(this);
    }

    @OnClick(R.id.fragment_translate_input_btn_mic)
    public void inputMicClick(){
        presenter.inputMic();
    }

    @OnClick(R.id.fragment_translate_input_btn_voice)
    public void inputVoiceClick(){
        presenter.inputVoice();
    }

    @OnClick(R.id.fragment_translate_output_btn_voice)
    public void outputVoiceClick(){
        presenter.outputVoice();
    }

    @OnClick(R.id.fragment_translate_output_btn_bookmark)
    public void addBookmarkClick(){
        presenter.addBookmark();
    }

    @OnClick(R.id.fragment_translate_btn_clear)
    public void clearInputClick(){
        binding.fragmentTranslateInput.setText(EMPTY_MESSAGE);
        binding.fragmentTranslateOutput.setText(EMPTY_MESSAGE);
        focusInput();
    }



    @OnClick(R.id.fragment_translate_swap)
    public void swapLanguageClick(){
        presenter.swapLanguage();
    }

    @OnClick(R.id.fragment_translate_layout_whiteboard)
    public void whiteboardClick(){
        focusInput();
    }

    private void focusInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.fragmentTranslateInput, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void showMessage(int message) {
        Toast.makeText(getContext(), res.getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void swapLanguage() {
        String temp = binding.fragmentTranslateLang1.getText().toString();
        binding.fragmentTranslateLang1.setText(binding.fragmentTranslateLang2.getText().toString());
        binding.fragmentTranslateLang2.setText(temp);
        if(!TextUtils.isEmpty(binding.fragmentTranslateOutput.getText().toString()) &&
                !TextUtils.isEmpty(binding.fragmentTranslateInput.getText().toString())){
            temp = binding.fragmentTranslateInput.getText().toString();
            binding.fragmentTranslateInput.setText(binding.fragmentTranslateOutput.getText().toString());
            binding.fragmentTranslateOutput.setText(temp);
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(timer!=null){
            timer.purge();
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TranslateTask(s.toString()),1000);
    }

    private class TranslateTask extends TimerTask {
        String text;

        public TranslateTask(String text){
            this.text = text;
        }

        @Override
        public void run() {
            presenter.translateText(text);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(!TextUtils.isEmpty(binding.fragmentTranslateInput.getText().toString())){
            outState.putString("input",binding.fragmentTranslateInput.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }
}
