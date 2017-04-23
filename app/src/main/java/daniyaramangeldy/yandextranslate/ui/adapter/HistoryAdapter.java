package daniyaramangeldy.yandextranslate.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniyaramangeldy.yandextranslate.R;
import daniyaramangeldy.yandextranslate.mvp.model.entity.Favourite;
import daniyaramangeldy.yandextranslate.mvp.model.entity.TranslateResponse;

/**
 * Created by daniyaramangeldy on 22.04.17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.BookmarksHolder> {
    private List<TranslateResponse> responseList;

    public HistoryAdapter(List<TranslateResponse> responseList) {
        this.responseList = responseList;
    }


    @Override
    public BookmarksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bookmarks_item, parent, false);
        return new BookmarksHolder(v);
    }

    @Override
    public void onBindViewHolder(BookmarksHolder holder, int position) {
        TranslateResponse item = responseList.get(position);
        holder.bindView(item);
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public void setList(List<TranslateResponse> responseList) {
        this.responseList = responseList;
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

        public BookmarksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindView(TranslateResponse response) {
            checkBox.setChecked(response.isFavourite());
            language.setText(response.getLang().toUpperCase());
            textOriginal.setText(response.getOriginalText());
            textTranslate.setHint(response.getText());
        }
    }
}
