package at.technikum_wien.miljevic.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import at.technikum_wien.miljevic.newsreader.news.NewsReaderAdapter;
import at.technikum_wien.miljevic.newsreader.news.NewsViewModel;
import at.technikum_wien.miljevic.newsreader.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mNewsListView;
    private NewsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        mNewsListView = findViewById(R.id.rv_news_reader);
        // assign layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNewsListView.setLayoutManager(layoutManager);
        mNewsListView.setHasFixedSize(false);

        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mViewModel.setNewsRssFeed(
                sharedPreferences.getString(
                        getString(R.string.settings_feed_url_key),
                        getString(R.string.settings_feed_url_default)));
        observeViewModel();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        switch (itemID) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_reload:
                mViewModel.reload();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void observeViewModel() {
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.settings_feed_url_key))) {
            mViewModel.setNewsRssFeed(
                    sharedPreferences.getString(
                            key,
                            getString(R.string.settings_feed_url_default)));
            mViewModel.reload();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}