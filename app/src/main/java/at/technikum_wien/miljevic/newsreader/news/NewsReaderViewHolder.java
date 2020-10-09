package at.technikum_wien.miljevic.newsreader.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import at.technikum_wien.miljevic.newsreader.R;

public class NewsReaderViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private TextView mItemTitle;
    private TextView mItemAuthor;
    private TextView mItemDate;

    public NewsReaderViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.iv_image);
        mItemTitle = itemView.findViewById(R.id.tv_title);
        mItemAuthor = itemView.findViewById(R.id.tv_author);
        mItemDate = itemView.findViewById(R.id.tv_date);
    }

    public void bind(NewsModel newsModel) {
        mItemTitle.setText(newsModel.getTitle());
        mItemAuthor.setText(newsModel.getAuthor());
        mItemDate.setText(NewsHelper.getLocalDate(newsModel.getPublicationDate()));
        Glide.with(itemView.getContext()).load(newsModel.getImage()).into(mImageView);
    }
}
