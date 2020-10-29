package at.technikum_wien.miljevic.newsreader.news;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.telecom.Call;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

import at.technikum_wien.miljevic.newsreader.AppExecutors;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;
import at.technikum_wien.miljevic.newsreader.repository.NewsRepository;

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
    private LiveData<List<NewsEntity>> mNewsEntries;
    private NewsRepository mNewsRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mNewsRepository = new NewsRepository(application);
        mNewsEntries = mNewsRepository.getNewsEntries();
        updateEntries();
    }

    public LiveData<List<NewsEntity>> getNewsEntries() {
        return mNewsEntries;
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

    public void delete(NewsEntity entry, Callback callback) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            try {
                mNewsRepository.delete(entry);
                callback.run(null);
            } catch (SQLiteConstraintException ex) {
                callback.run(Error.deleteError);
            } catch (Exception ex) {
                callback.run(Error.generalError);
            }
        });
    }

    private void updateEntries() {
        AppExecutors.getInstance().networkIO().execute(() -> {
            List<NewsEntity> newsEntries = loadNewsData();
            for (NewsEntity entry : newsEntries) {
                insert(entry, error -> {
                    if (error != null) {
                        Log.e(TAG, "Inserting new news entry failed");
                        return;
                    }
                    Log.d(TAG, "New entry '" + entry.getUniqueId() + "' successfully inserted to DB.");
                });
            }
        });
    }

    private List<NewsEntity> loadNewsData() {
        HttpURLConnection urlConnection = null;
        List<NewsEntity> newsData = new LinkedList<>();
        try {
            URL url = new URL(mNewsRssFeed);
            urlConnection = (HttpURLConnection) url.openConnection();
            // closed after parsing
            InputStream is = urlConnection.getInputStream();
            newsData = new NewsXmlParser().parse(is);
        } catch (MalformedURLException e) {
            Log.e(TAG, "URL error occurred while loading news data", e);
        } catch (IOException e) {
            Log.e(TAG, "Opening URL connection failed while loading news data", e);
        } catch (XmlPullParserException | ParseException e) {
            Log.e(TAG, "Parsing XML data failed while loading news data", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return newsData;
    }

    public void reload() {
        updateEntries();
    }

    public void setNewsRssFeed(String rssFeed) {
        this.mNewsRssFeed = rssFeed;
    }

}
