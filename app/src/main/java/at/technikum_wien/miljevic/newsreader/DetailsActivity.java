package at.technikum_wien.miljevic.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private TextView titleText;
    private TextView authorText;
    private TextView dateText;
    private TextView descriptionText;
    private TextView keywordsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleText = findViewById(R.id.details_title);
        authorText = findViewById(R.id.details_author);
        dateText = findViewById(R.id.details_date);
        descriptionText = findViewById(R.id.details_description);
        keywordsText = findViewById(R.id.details_keywords);

        if (getIntent().hasExtra(NewsHelper.NEWS_ITEM_EXTRA)) {
            NewsModel newsModel = getIntent().getParcelableExtra(NewsHelper.NEWS_ITEM_EXTRA);
            assert newsModel != null;
            titleText.setText(newsModel.getTitle());
            authorText.setText(newsModel.getAuthor());
            dateText.setText(NewsHelper.getLocalDate(newsModel.getPublicationDate()));
            descriptionText.setText(newsModel.getDescription());
            keywordsText.setText("#" + String.join(", ", newsModel.getKeywords()));
        } else {
            Log.e(TAG, "Intent has no news item data");
        }
    }
}