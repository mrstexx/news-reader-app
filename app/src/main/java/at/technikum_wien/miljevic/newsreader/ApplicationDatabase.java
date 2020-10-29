package at.technikum_wien.miljevic.newsreader;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import at.technikum_wien.miljevic.newsreader.dao.NewsDao;
import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;

@Database(entities = {NewsEntity.class}, version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {
    private static final String LOG_TAG = ApplicationDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "telephone_book";
    private static ApplicationDatabase sInstance;
    private static final Object sLock = new Object();

    public static ApplicationDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    Log.d(LOG_TAG, "Creating new database instance.");
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ApplicationDatabase.class, ApplicationDatabase.DATABASE_NAME).build();
                }
            }
        }
        Log.d(LOG_TAG, "Returning current database instance.");
        return sInstance;
    }

    public abstract NewsDao newsDao();
}
