package at.technikum_wien.miljevic.newsreader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsReaderAdapter extends RecyclerView.Adapter<NewsReaderViewHolder> {

    private List<NewsModel> newsList;

    NewsReaderAdapter(List<NewsModel> newsList) {
        this.newsList = newsList;
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
        holder.bind(newsList.get(position));
        holder.itemView.setOnClickListener(view -> {
            final Context context = view.getContext();
            final Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(NewsHelper.NEWS_ITEM_EXTRA, newsList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
