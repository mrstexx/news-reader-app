package at.technikum_wien.miljevic.newsreader.news;

import android.app.Application;
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

public class NewsViewModel extends AndroidViewModel {

    private static final String TAG = NewsViewModel.class.getSimpleName();
    private String mNewsRssFeed;
    private MutableLiveData<List<NewsModel>> mNewsEntries;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mNewsEntries = new MutableLiveData<>();
        updateEntries();
    }

    public LiveData<List<NewsModel>> getNewsEntries() {
        return mNewsEntries;
    }

    private void updateEntries() {
        mNewsEntries.setValue(null);
        Executors.newSingleThreadExecutor().execute(() -> {
            List<NewsModel> newsModels = loadNewsData();
            mNewsEntries.postValue(newsModels);
        });
    }

    private List<NewsModel> loadNewsData() {
        HttpURLConnection urlConnection = null;
        List<NewsModel> newsData = new LinkedList<>();
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
