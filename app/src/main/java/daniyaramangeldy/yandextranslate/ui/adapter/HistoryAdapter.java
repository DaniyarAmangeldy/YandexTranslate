package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

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
 * Created by daniyaramangeldy on 22.04.17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.BookmarksHolder> {
    private static final String TAG = "HistoryAdapter";
    private List<TranslateResponse> responseList;
    private List<TranslateResponse> filterList;
    private onClickListener clickListener;


    public interface onClickListener {
        void onClick(String text);

        void onLongClick(View v, String text, int position);

        void onCheckFavourite(TranslateResponse response, boolean checked,int position);
    }

    public void setOnClickListener(onClickListener listener) {
        this.clickListener = listener;
    }

    public HistoryAdapter(List<TranslateResponse> responseList) {
        this.responseList = responseList;
        this.filterList = new ArrayList<>();
        this.filterList.addAll(responseList);
    }


    @Override
    public BookmarksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bookmarks_item, parent, false);
        return new BookmarksHolder(v);
    }

    @Override
    public void onBindViewHolder(BookmarksHolder holder, int position) {
        TranslateResponse item = filterList.get(position);
        holder.bindView(item);
    }


    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public void setList(List<TranslateResponse> responseList) {
        this.responseList = responseList;
        this.filterList.clear();
        this.filterList.addAll(responseList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
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
            for (TranslateResponse item : responseList) {
                if (item.getOriginalText().toLowerCase().contains(filter) || item.getText().toLowerCase().contains(filter)) {
                    filterList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    class BookmarksHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bookmarks_item_btn_bookmark)
        AppCompatCheckBox checkBox;
        @BindView(R.id.bookmarks_item_lang)
        TextView language;
        @BindView(R.id.bookmarks_item_text_original)
        TextView textOriginal;
        @BindView(R.id.bookmarks_item_text_translate)
        TextView textTranslate;
        private boolean checkFlag = false;

        public BookmarksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindView(TranslateResponse response) {
            checkFlag = false;
            checkBox.setChecked(response.isFavourite());
            language.setText(response.getLang().toUpperCase());
            textOriginal.setText(response.getOriginalText());
            textTranslate.setHint(response.getText());
            checkFlag = true;
        }

        @OnClick(R.id.bookmarks_item_container)
        public void holderClick() {
            if (clickListener != null) {
                clickListener.onClick(filterList.get(getAdapterPosition()).getOriginalText());
            }
        }

        @OnLongClick(R.id.bookmarks_item_container)
        public boolean holderLongClick(View v) {
            if (clickListener != null) {
                clickListener.onLongClick(v, filterList.get(getAdapterPosition()).getOriginalText(), getAdapterPosition());
            }
            return true;
        }

        @OnCheckedChanged(R.id.bookmarks_item_btn_bookmark)
        public void favouriteCheck(boolean isChecked) {
            if (clickListener != null && checkFlag) {
                clickListener.onCheckFavourite(filterList.get(getAdapterPosition()),isChecked,getAdapterPosition());
            }
        }
    }
}
