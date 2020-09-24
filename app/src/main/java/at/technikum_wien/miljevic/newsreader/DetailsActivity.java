package at.technikum_wien.miljevic.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private TextView mTitleText;
    private TextView mAuthorText;
    private TextView mDateText;
    private TextView mDescriptionText;
    private TextView mKeywordsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mTitleText = findViewById(R.id.details_title);
        mAuthorText = findViewById(R.id.details_author);
        mDateText = findViewById(R.id.details_date);
        mDescriptionText = findViewById(R.id.details_description);
        mKeywordsText = findViewById(R.id.details_keywords);

        if (getIntent().hasExtra(NewsHelper.NEWS_ITEM_EXTRA)) {
            NewsModel newsModel = getIntent().getParcelableExtra(NewsHelper.NEWS_ITEM_EXTRA);
            assert newsModel != null;
            mTitleText.setText(newsModel.getTitle());
            mAuthorText.setText(newsModel.getAuthor());
            mDateText.setText(NewsHelper.getLocalDate(newsModel.getPublicationDate()));
            mDescriptionText.setText(newsModel.getDescription());
            mKeywordsText.setText("#" + String.join(", ", newsModel.getKeywords()));
        } else {
            Log.e(TAG, "Intent has no news item data");
        }
    }
}