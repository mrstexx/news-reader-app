package at.technikum_wien.miljevic.newsreader.utils;

import android.icu.text.DateFormat;

import java.util.Date;

public class NewsHelper {

    private static final DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
    public static final String NEWS_ITEM_EXTRA = "news_item";

    public static String getLocalDate(Date date) {
        return formatter.format(date);
    }

}
