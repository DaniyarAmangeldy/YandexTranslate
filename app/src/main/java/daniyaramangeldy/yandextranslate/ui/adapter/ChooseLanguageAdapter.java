package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Language;

/**
 * Created by daniyaramangeldy on 24.04.17.
 */

public class ChooseLanguageAdapter extends RecyclerView.Adapter<ChooseLanguageAdapter.LanguageViewHolder> {
    private List<Language> langList;
    private String current;
    private onClickListener clickListener;

    public interface onClickListener{
        void onClick(String key,String value);
    }

    public void setOnClickListener(onClickListener listener){
        this.clickListener = listener;
    }

    public ChooseLanguageAdapter(List<Language> langList, String current){
        this.langList = langList;
        this.current = current;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_choose_language,parent,false);
        return new LanguageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, int position) {
        Language lang = langList.get(position);
        holder.bindView(lang);
    }

    @Override
    public int getItemCount() {
        return langList.size();
    }

    class LanguageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.choose_language_text)
        TextView text;
        @BindView(R.id.choose_language_check)
        ImageView check;

        public LanguageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        private void bindView(Language lang){
            text.setText(lang.getName());
            if(lang.getId_().equals(current)){
                check.setVisibility(View.VISIBLE);
            }else{
                check.setVisibility(View.INVISIBLE);
            }
        }

        @OnClick(R.id.choose_language_container)
        public void languageClick(){
            if(clickListener!=null){
                Language lang = langList.get(getAdapterPosition());
                clickListener.onClick(lang.getId_(),lang.getName());
            }
        }
    }
}
