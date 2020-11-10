package at.technikum_wien.miljevic.newsreader.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.time.ZonedDateTime;
import java.util.List;

import at.technikum_wien.miljevic.newsreader.AppExecutors;
import at.technikum_wien.miljevic.newsreader.ApplicationDatabase;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;
import at.technikum_wien.miljevic.newsreader.news.NewsViewModel;
import at.technikum_wien.miljevic.newsreader.repository.NewsRepository;
import at.technikum_wien.miljevic.newsreader.services.NewsTasks;
import at.technikum_wien.miljevic.newsreader.utils.NotificationUtils;

public class NewsWorker extends Worker {
    private static final String LOG_TAG = NewsWorker.class.getCanonicalName();
    public static final String RSS_FEED_URL = "rss_feed_url";

    private final NewsRepository newsRepository;
    private final Context mContext;
    private final LiveData<List<NewsEntity>> mEntries;

    public NewsWorker(@NonNull Context context, @NonNull WorkerParameters parameters) {
        super(context, parameters);
        newsRepository = new NewsRepository(context);
        mEntries = newsRepository.getNewsEntries();
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(LOG_TAG, "Running worker to download and update news.......");
        // download and proceed news data here
        String rssFeedUrl = getInputData().getString(RSS_FEED_URL);
        List<NewsEntity> newsData = NewsTasks.downloadNews(rssFeedUrl);
        List<NewsEntity> oldData = newsRepository.getEntriesAsList();
        // for remove entries that are older than 5 days
        for (NewsEntity newsEntity : oldData) {
            if (isOldNewsEntry(newsEntity)) {
                Log.d(LOG_TAG, "REMOVE: removing entry with the id '" + newsEntity.getUniqueId() + "'");
                try {
                    newsRepository.delete(newsEntity);
                    Log.d(LOG_TAG, "REMOVE: removing entry with the id '" + newsEntity.getUniqueId() + "'");
                } catch (Exception ex) {
                    Log.e(LOG_TAG, "REMOVE FAILED: ", ex);
                }
            }
        }
        // add new entries
        for (NewsEntity newsEntity : newsData) {
            try {
                newsRepository.insert(newsEntity);
                Log.d(LOG_TAG, "INSERT/UPDATE: inserting/updating entry with the id '" + newsEntity.getUniqueId() + "'");
                if (newsRepository.getEntryByUniqueId(newsEntity.getUniqueId()) == null) {
                    showNotification(newsEntity);
                }
            } catch (Exception ex) {
                Log.e(LOG_TAG, "INSERT/UPDATE FAILED: ", ex);
            }
        }
        return Result.success();
    }

    private static boolean isOldNewsEntry(NewsEntity entity) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime fiveDaysAgo = now.plusDays(-5);
        return entity.getPublicationDate().toInstant().isBefore(fiveDaysAgo.toInstant());
    }

    private void showNotification(NewsEntity entry) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel 01";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(NotificationUtils.CHANNEL_ID, name, importance);
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationUtils.createNotification(mContext, entry, null);
    }
}
