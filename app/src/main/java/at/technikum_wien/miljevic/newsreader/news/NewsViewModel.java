package at.technikum_wien.miljevic.newsreader.news;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import at.technikum_wien.miljevic.newsreader.AppExecutors;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;
import at.technikum_wien.miljevic.newsreader.repository.NewsRepository;
import at.technikum_wien.miljevic.newsreader.services.NewsIntentService;
import at.technikum_wien.miljevic.newsreader.worker.NewsWorker;

public class NewsViewModel extends AndroidViewModel {
    enum Error {
        generalError,
        insertError,
        deleteError,
    }

    interface Callback {
        void run(Error error);
    }

    private static final String TAG = NewsViewModel.class.getSimpleName();
    private String mNewsRssFeed;
    private final LiveData<List<NewsEntity>> mNewsEntries;
    private final NewsRepository mNewsRepository;
    private final Object lock = new Object();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mNewsRepository = new NewsRepository(application);
        mNewsEntries = mNewsRepository.getNewsEntries();
    }

    public LiveData<List<NewsEntity>> getNewsEntries() {
        return mNewsEntries;
    }

    private boolean isEmptyViewModel() {
        return mNewsEntries == null ||
                mNewsEntries.getValue() == null ||
                mNewsEntries.getValue().isEmpty();
    }

    public void startInitService(NewsIntentService.Callback callback) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (isEmptyViewModel()) {
                callback.run();
            }
        });
    }

    public void loadDataFromWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data.Builder inputData = new Data.Builder();
        inputData.putString(NewsWorker.RSS_FEED_URL, mNewsRssFeed);
        PeriodicWorkRequest downloadWorkRequest = new PeriodicWorkRequest
                .Builder(NewsWorker.class, 30, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(inputData.build())
                .build();
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                TAG,
                ExistingPeriodicWorkPolicy.REPLACE,
                downloadWorkRequest
        );
    }

    public void insert(NewsEntity entry, Callback callback) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            try {
                mNewsRepository.insert(entry);
                callback.run(null);
            } catch (SQLiteConstraintException ex) {
                callback.run(Error.insertError);
            } catch (Exception ex) {
                callback.run(Error.generalError);
            }
        });
    }

    private void insertNewEntries(List<NewsEntity> newEntries) {
        for (NewsEntity entry : newEntries) {
            insert(entry, error -> {
                if (error != null) {
                    Log.e(TAG, "Inserting new news entry failed");
                    return;
                }
                Log.d(TAG, "INSERT: New entry '" + entry.getUniqueId() + "' successfully inserted to DB.");
            });
        }
    }

    private void updateEntries(List<NewsEntity> newsEntities) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (!newsEntities.isEmpty()) {
                insertNewEntries(newsEntities);
            }
        });
    }

    public void reload() {

    }

    public void updateNewsData(List<NewsEntity> newsEntities) {
        updateEntries(newsEntities);
    }

    public void setNewsRssFeed(String rssFeed) {
        this.mNewsRssFeed = rssFeed;
    }

    public String getNewsRssFeed() {
        return mNewsRssFeed;
    }
}
