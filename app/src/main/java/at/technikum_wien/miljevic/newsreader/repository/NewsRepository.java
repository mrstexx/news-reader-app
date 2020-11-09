package at.technikum_wien.miljevic.newsreader.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import at.technikum_wien.miljevic.newsreader.ApplicationDatabase;
import at.technikum_wien.miljevic.newsreader.dao.NewsDao;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;

public class NewsRepository {
    private static final String LOG_TAG = NewsRepository.class.getSimpleName();
    private NewsDao mNewsDao;
    private LiveData<List<NewsEntity>> mEntries;

    public NewsRepository(Application application) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(application);
        init(db);
    }

    public NewsRepository(Context context) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(context);
        init(db);
    }

    private void init(ApplicationDatabase db) {
        mNewsDao = db.newsDao();
        mEntries = mNewsDao.getEntries();
    }

    public LiveData<List<NewsEntity>> getNewsEntries() {
        return mEntries;
    }

    public NewsEntity getEntryByUniqueId(String uniqueId) {
        return mNewsDao.getEntryByUniqueId(uniqueId);
    }

    public void insert(NewsEntity entry) {
        mNewsDao.insert(entry);
    }

    public void delete(NewsEntity entry) {
        mNewsDao.delete(entry);
    }

    public void update(NewsEntity entry) {
        mNewsDao.update(entry);
    }
}