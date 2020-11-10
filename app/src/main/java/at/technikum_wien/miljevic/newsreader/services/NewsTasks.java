package at.technikum_wien.miljevic.newsreader.services;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;
import at.technikum_wien.miljevic.newsreader.utils.NewsXmlParser;

public class NewsTasks {

    private static final String TAG = NewsTasks.class.getSimpleName();

    // to be called only when database is empty
    public static List<NewsEntity> downloadNews(String rssFeedUrl) {
        HttpURLConnection urlConnection = null;
        List<NewsEntity> newsData = new LinkedList<>();
        try {
            URL url = new URL(rssFeedUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            // closed after parsing
            InputStream is = urlConnection.getInputStream();
            newsData = new NewsXmlParser().parse(is);
        } catch (MalformedURLException e) {
            Log.e(TAG, "URL error occurred while loading news data", e);
        } catch (IOException e) {
            Log.e(TAG, "Opening URL connection failed while loading news data", e);
        } catch (XmlPullParserException | ParseException e) {
            Log.e(TAG, "Parsing XML data failed while loading news data", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return newsData;
    }
}
