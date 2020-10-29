package at.technikum_wien.miljevic.newsreader.dao;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "news", indices = {@Index(value = {"title", "uniqueId"}, unique = true)})
public class NewsEntity implements Serializable {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    @NonNull
    private String title;

    @ColumnInfo
    private String description;

    @ColumnInfo
    @NonNull
    private String author;

    @ColumnInfo
    @NonNull
    private String link;

    @ColumnInfo
    @NonNull
    private String uniqueId;

    @ColumnInfo
    @NonNull
    private String image;

    @ColumnInfo
    @NonNull
    @TypeConverters({NewsConverter.class})
    private Date publicationDate;

    @ColumnInfo
    @NonNull
    @TypeConverters({NewsConverter.class})
    private List<String> keywords;

    public NewsEntity(long id,
                      @NonNull String title,
                      String description,
                      @NonNull String author,
                      @NonNull String link,
                      @NonNull String uniqueId,
                      @NonNull String image,
                      @NonNull Date publicationDate,
                      @NonNull List<String> keywords) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.link = link;
        this.uniqueId = uniqueId;
        this.image = image;
        this.publicationDate = publicationDate;
        this.keywords = keywords;
    }

    @Ignore
    public NewsEntity(@NonNull String title,
                      String description,
                      @NonNull String author,
                      @NonNull String link,
                      @NonNull String uniqueId,
                      @NonNull String image,
                      @NonNull Date publicationDate,
                      @NonNull List<String> keywords) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.link = link;
        this.uniqueId = uniqueId;
        this.image = image;
        this.publicationDate = publicationDate;
        this.keywords = keywords;
    }

    @Ignore
    public NewsEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    @NonNull
    public String getLink() {
        return link;
    }

    public void setLink(@NonNull String link) {
        this.link = link;
    }

    @NonNull
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(@NonNull String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }

    @NonNull
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(@NonNull Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @NonNull
    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(@NonNull List<String> keywords) {
        this.keywords = keywords;
    }
}