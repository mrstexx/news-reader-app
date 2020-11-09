package at.technikum_wien.miljevic.newsreader.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.preference.PreferenceManager;

import at.technikum_wien.miljevic.newsreader.R;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;
import at.technikum_wien.miljevic.newsreader.details.DetailsActivity;

public class NotificationUtils {

    private static final String LOG_TAG = NotificationUtils.class.getCanonicalName();
    private static final String NOTIFICATION_ID_KEY = "notification_id";
    public static final String CHANNEL_ID = "my_channel_01";// The id of the channel.
    public static final String NOTIFICATION_EXTRA = "notification_extra";

    public static void createNotification(Context context, NewsEntity entry, Bitmap bitmap) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int notificationId = sharedPreferences.getInt(NOTIFICATION_ID_KEY, 0);
        notificationId++;
        sharedPreferences.edit().putInt(NOTIFICATION_ID_KEY, notificationId).apply();

        NotificationCompat.Builder notificationBuilder;
        // in our case not needed as minsdk is 29
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(entry.getTitle())
                    .setAutoCancel(true);
        } else {
            notificationBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(entry.getTitle())
                    .setAutoCancel(true);
        }

        if (bitmap != null) {
            notificationBuilder.setLargeIcon(bitmap);
        }

        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(NewsHelper.NEWS_ITEM_EXTRA, entry);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(
                notificationId,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}