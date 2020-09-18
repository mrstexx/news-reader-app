package at.technikum_wien.miljevic.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LIST_KEY = "listKey";

    private List<NewsModel> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView newsListView = findViewById(R.id.rv_news_reader);
        // assign layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsListView.setLayoutManager(layoutManager);
        newsListView.setHasFixedSize(false);
        // check for saved instance data. maybe with dummy data it makes no sense, but later
        // we will need it
        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_KEY)) {
            newsList = savedInstanceState.getParcelableArrayList(LIST_KEY);
        } else {
            NewsHelper.readDummyData(newsList);
        }

        // create and set adapter
        NewsReaderAdapter newsReaderAdapter = new NewsReaderAdapter(newsList);
        newsListView.setAdapter(newsReaderAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList(LIST_KEY, (ArrayList<? extends Parcelable>) newsList);
    }
}