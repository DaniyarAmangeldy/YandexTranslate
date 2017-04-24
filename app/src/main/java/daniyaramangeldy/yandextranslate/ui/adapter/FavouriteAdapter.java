package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnLongClick;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Created by daniyaramangeldy on 23.04.17.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder> {

    private List<Favourite> responseList;
    private List<Favourite> filterList;
    private onClickListener clickListener;

    public interface onClickListener {
        void onClick(String id);

        void onCheckFavourite(Favourite favourite, boolean checked,int position);
    }

    public void setOnClickListener(onClickListener listener) {
        this.clickListener = listener;
    }

    public FavouriteAdapter(List<Favourite> responseList) {
        this.responseList = responseList;
        this.filterList = new ArrayList<>();
        this.filterList.addAll(responseList);
    }


    @Override
    public FavouriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bookmarks_item, parent, false);
        return new FavouriteHolder(v);
    }

    @Override
    public void onBindViewHolder(FavouriteHolder holder, int position) {
        Favourite item = filterList.get(position);
        holder.bindView(item);
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public void setList(List<Favourite> responseList) {
        this.responseList = responseList;
        this.filterList.clear();
        this.filterList.addAll(responseList);
        notifyDataSetChanged();
    }

    public void removeFavourite(int position){
        for(int i=0;i<responseList.size();i++){
            if(responseList.get(i).getId().equals(filterList.get(position).getId())){
                responseList.remove(i);
            }
        }
        this.filterList.remove(position);
        notifyItemRemoved(position);
    }

    public void filterText(String filter) {
        filterList.clear();
        if (filter.isEmpty()) {
            filterList.addAll(responseList);
        } else {
            filter = filter.toLowerCase();
            for (Favourite item : responseList) {
                if (item.getOriginalText().toLowerCase().contains(filter) || item.getText().toLowerCase().contains(filter)) {
                    filterList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    class FavouriteHolder extends RecyclerView.ViewHolder {

        private boolean checkFlag = false;

        @BindView(R.id.bookmarks_item_btn_bookmark)
        AppCompatCheckBox checkBox;
        @BindView(R.id.bookmarks_item_lang)
        TextView language;
        @BindView(R.id.bookmarks_item_text_original)
        TextView textOriginal;
        @BindView(R.id.bookmarks_item_text_translate)
        TextView textTranslate;

        public FavouriteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindView(Favourite response) {
            checkFlag = false;
            checkBox.setChecked(response.isFavourite());
            language.setText(response.getLang().toUpperCase());
            textOriginal.setText(response.getOriginalText());
            textTranslate.setHint(response.getText());
            checkFlag = true;
        }

        @OnClick(R.id.bookmarks_item_container)
        public void itemClick() {
            if (clickListener != null) {
                clickListener.onClick(filterList.get(getAdapterPosition()).getOriginalText());
            }
        }


        @OnCheckedChanged(R.id.bookmarks_item_btn_bookmark)
        public void toggleBookmark(boolean isChecked) {
            if (clickListener != null && checkFlag) {
                clickListener.onCheckFavourite(filterList.get(getAdapterPosition()),isChecked,getAdapterPosition());
            }
        }
    }
}
