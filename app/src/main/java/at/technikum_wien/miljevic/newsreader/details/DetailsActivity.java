package at.technikum_wien.miljevic.newsreader.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import at.technikum_wien.miljevic.newsreader.R;
import at.technikum_wien.miljevic.newsreader.news.NewsHelper;
import at.technikum_wien.miljevic.newsreader.news.NewsModel;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private ImageView mImageView;
    private TextView mTitleText;
    private TextView mAuthorText;
    private TextView mDateText;
    private TextView mDescriptionText;
    private TextView mKeywordsText;
    private Button mFullStoryBtn;

    private NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mImageView = findViewById(R.id.iv_image);
        mTitleText = findViewById(R.id.details_title);
        mAuthorText = findViewById(R.id.details_author);
        mDateText = findViewById(R.id.details_date);
        mDescriptionText = findViewById(R.id.details_description);
        mKeywordsText = findViewById(R.id.details_keywords);
        mFullStoryBtn = findViewById(R.id.btn_full_story);

        if (getIntent().hasExtra(NewsHelper.NEWS_ITEM_EXTRA)) {
            newsModel = getIntent().getParcelableExtra(NewsHelper.NEWS_ITEM_EXTRA);
            assert newsModel != null;
            mTitleText.setText(newsModel.getTitle());
            mAuthorText.setText(newsModel.getAuthor());
            mDateText.setText(NewsHelper.getLocalDate(newsModel.getPublicationDate()));
            mDescriptionText.setText(newsModel.getDescription());
            mKeywordsText.setText("#" + String.join(", ", newsModel.getKeywords()));
            mFullStoryBtn.setOnClickListener(this::onFullStoryButtonClick);
            Glide.with(this)
                    .load(newsModel.getImage())
                    .onlyRetrieveFromCache(true)
                    .placeholder(R.drawable.progress_anim)
                    .error(R.drawable.ic_img_placeholder)
                    .into(mImageView);
        } else {
            mFullStoryBtn.setClickable(false);
            Toast.makeText(this, R.string.no_available_data, Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Intent has no news item data");
        }
    }

    private void onFullStoryButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsModel.getLink()));
        startActivity(browserIntent);
    }
}