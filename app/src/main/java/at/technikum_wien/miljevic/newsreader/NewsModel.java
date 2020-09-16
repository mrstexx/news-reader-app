package at.technikum_wien.miljevic.newsreader;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class NewsModel implements Parcelable {

    private String title;
    private String description;
    private String author;
    private Date publicationDate;
    private List<String> keywords;

    public NewsModel(String title, String description, String author, Date publicationDate, List<String> keywords) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publicationDate = publicationDate;
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(author);
        parcel.writeValue(publicationDate);
        parcel.writeStringList(keywords);
    }
}
