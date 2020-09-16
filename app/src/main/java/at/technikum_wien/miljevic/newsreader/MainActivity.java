package at.technikum_wien.miljevic.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LIST_KEY = "listKey";

    private NewsReaderAdapter newsReaderAdapter;
    private RecyclerView newsListView;
    private List<NewsModel> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = new ArrayList<>();
        addDummyNewsData();

        newsListView = findViewById(R.id.rv_news_reader);
        // assign layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsListView.setLayoutManager(layoutManager);
        newsListView.setHasFixedSize(false);

        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_KEY)) {
            newsList = savedInstanceState.getParcelableArrayList(LIST_KEY);
        }

        newsReaderAdapter = new NewsReaderAdapter(newsList);
        newsListView.setAdapter(newsReaderAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList(LIST_KEY, (ArrayList<? extends Parcelable>) newsList);
    }

    private void addDummyNewsData() {
        newsList.add(new NewsModel("This is test one",
                "Bla bla...",
                "SM",
                new Date(),
                new ArrayList<String>()));
        newsList.add(new NewsModel("This is test two",
                "Bla bla...",
                "SM",
                new Date(),
                new ArrayList<String>()));
    }
}