package at.technikum_wien.miljevic.newsreader.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publicationDate DESC")
    LiveData<List<NewsEntity>> getEntries();

    @Query("SELECT * FROM news ORDER BY publicationDate DESC")
    List<NewsEntity> getEntriesAsList();

    @Query("SELECT * FROM news WHERE _id=:id")
    LiveData<NewsEntity> getEntryById(long id);

    @Query("SELECT * FROM news WHERE uniqueId=:uniqueId")
    NewsEntity getEntryByUniqueId(String uniqueId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsEntity entry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(NewsEntity entry);

    @Delete
    void delete(NewsEntity entry);
}
