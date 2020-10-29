package at.technikum_wien.miljevic.newsreader.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import at.technikum_wien.miljevic.newsreader.R;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;

public class NewsReaderViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private TextView mItemTitle;
    private TextView mItemAuthor;
    private TextView mItemDate;

    private boolean mDisplayImages;

    public NewsReaderViewHolder(@NonNull View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        mImageView = itemView.findViewById(R.id.iv_image);
        mItemTitle = itemView.findViewById(R.id.tv_title);
        mItemAuthor = itemView.findViewById(R.id.tv_author);
        mItemDate = itemView.findViewById(R.id.tv_date);

        mDisplayImages = sharedPreferences.getBoolean(
                context.getString(R.string.settings_display_image_key),
                context.getResources().getBoolean(R.bool.settings_display_images_default));
    }

    public void bind(NewsEntity newsEntity) {
        mItemTitle.setText(newsEntity.getTitle());
        mItemAuthor.setText(newsEntity.getAuthor());
        mItemDate.setText(NewsHelper.getLocalDate(newsEntity.getPublicationDate()));
        displayImage(newsEntity.getImage());
    }

    private void displayImage(String imageUrl) {
        if (mDisplayImages) {
            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.progress_anim)
                    .error(R.drawable.ic_img_placeholder)
                    .into(mImageView);
        } else {
            mImageView.setVisibility(View.GONE);
        }
    }
}
