package at.technikum_wien.miljevic.newsreader;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class NewsModel implements Parcelable {

    private String title;
    private String description;
    private String author;
    private String link;
    private String uniqueId;
    private String image;
    private Date publicationDate;
    private List<String> keywords;

    public NewsModel() {
    }

    public NewsModel(String title, String description, String author, Date publicationDate, List<String> keywords) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publicationDate = publicationDate;
        this.keywords = keywords;
    }

    protected NewsModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        author = in.readString();
        link = in.readString();
        uniqueId = in.readString();
        image = in.readString();
        publicationDate = (Date) in.readSerializable();
        keywords = in.createStringArrayList();
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        parcel.writeString(link);
        parcel.writeString(uniqueId);
        parcel.writeString(image);
        parcel.writeSerializable(publicationDate);
        parcel.writeStringList(keywords);
    }
}
