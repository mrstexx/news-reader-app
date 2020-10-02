package at.technikum_wien.miljevic.newsreader.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.technikum_wien.miljevic.newsreader.details.DetailsActivity;
import at.technikum_wien.miljevic.newsreader.R;

public class NewsReaderAdapter extends RecyclerView.Adapter<NewsReaderViewHolder> {

    private List<NewsModel> mNewsList;

    public NewsReaderAdapter(List<NewsModel> newsList) {
        this.mNewsList = newsList;
    }

    @NonNull
    @Override
    public NewsReaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIDForListItem = R.layout.news_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIDForListItem, parent, false);
        return new NewsReaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsReaderViewHolder holder, int position) {
        holder.bind(mNewsList.get(position));
        holder.itemView.setOnClickListener(view -> {
            final Context context = view.getContext();
            final Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(NewsHelper.NEWS_ITEM_EXTRA, mNewsList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
