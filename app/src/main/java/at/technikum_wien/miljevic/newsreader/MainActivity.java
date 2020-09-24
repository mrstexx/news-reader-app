package at.technikum_wien.miljevic.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mNewsListView;
    private NewsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsListView = findViewById(R.id.rv_news_reader);
        // assign layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNewsListView.setLayoutManager(layoutManager);
        mNewsListView.setHasFixedSize(false);

        setupViewModel();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void setupViewModel() {
        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mViewModel.getNewsEntries().observe(this, newsModels -> {
            if (newsModels == null) {
                return;
            }
            if (newsModels.size() == 0) {
                Toast.makeText(this, R.string.no_available_news, Toast.LENGTH_SHORT).show();
            }
            // create and set adapter
            mNewsListView.setAdapter(new NewsReaderAdapter(newsModels));
        });
    }
}