package at.technikum_wien.miljevic.newsreader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsReaderViewHolder extends RecyclerView.ViewHolder {

    private TextView mListItemTitle;

    public NewsReaderViewHolder(@NonNull View itemView) {
        super(itemView);
        mListItemTitle = itemView.findViewById(R.id.tv_item_title);
    }

    public void bind(NewsModel newsModel) {
        mListItemTitle.setText(newsModel.getTitle());
    }
}
