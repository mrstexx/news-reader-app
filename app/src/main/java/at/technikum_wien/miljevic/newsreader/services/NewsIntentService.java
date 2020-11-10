package at.technikum_wien.miljevic.newsreader.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;

public class NewsIntentService extends IntentService {
    public static final String DOWNLOAD_TASK = "download_news";
    public static final String RELOAD_TASK = "reload_news";
    public static final String DOWNLOAD_TASK_OUT = "download_news_out";

    public interface Callback {
        void run();
    }

    public NewsIntentService() {
        super("NewsIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.getAction().equals(DOWNLOAD_TASK) || intent.getAction().equals(RELOAD_TASK)) {
            String rssFeedUrl = intent.getStringExtra("rssFeed");
            List<NewsEntity> newsEntities = NewsTasks.downloadNews(rssFeedUrl);
            // create new response intent
            Intent intentResponse = new Intent();
            intentResponse.setAction(intent.getAction());
            intentResponse.putExtra(DOWNLOAD_TASK_OUT, (Serializable) newsEntities);
            sendBroadcast(intentResponse);
        }
    }
}
