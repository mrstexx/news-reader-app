package at.technikum_wien.miljevic.newsreader.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publicationDate")
    LiveData<List<NewsEntity>> getEntries();

    @Query("SELECT * FROM news WHERE _id=:id")
    LiveData<NewsEntity> getEntryById(long id);

    @Insert
    void insert(NewsEntity entry);

    @Update
    void update(NewsEntity entry);

    @Delete
    void delete(NewsEntity entry);
}
