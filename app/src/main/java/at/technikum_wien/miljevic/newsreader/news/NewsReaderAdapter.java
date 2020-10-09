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
    public static final int VIEW_TYPE_TOP = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<NewsModel> mNewsList;

    public NewsReaderAdapter(List<NewsModel> newsList) {
        this.mNewsList = newsList;
    }

    @NonNull
    @Override
    public NewsReaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIDForListItem;
        switch (viewType) {
            case VIEW_TYPE_TOP:
                layoutIDForListItem = R.layout.news_list_item_top;
                break;
            case VIEW_TYPE_NORMAL:
                layoutIDForListItem = R.layout.news_list_item;
                break;
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
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
        return mNewsList == null ? 0 : mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_TOP;
        }
        return VIEW_TYPE_NORMAL;
    }
}
