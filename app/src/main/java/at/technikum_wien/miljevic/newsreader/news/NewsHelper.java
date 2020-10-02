package at.technikum_wien.miljevic.newsreader.news;

import android.icu.text.DateFormat;

import java.util.Date;

public class NewsHelper {

    private static final DateFormat formatter = DateFormat.getDateTimeInstance();
    public static final String NEWS_ITEM_EXTRA = "news_item";

    public static String getLocalDate(Date date) {
        return formatter.format(date);
    }

}
