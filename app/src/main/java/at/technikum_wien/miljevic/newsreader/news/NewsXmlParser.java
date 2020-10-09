package at.technikum_wien.miljevic.newsreader.news;

import android.icu.text.SimpleDateFormat;
import android.text.Html;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsXmlParser {

    private static final String ns = null;

    public List<NewsModel> parse(InputStream in) throws XmlPullParserException, IOException, ParseException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, "UTF_8");
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<NewsModel> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException, ParseException {
        List<NewsModel> newsEntries = new LinkedList<>();
        parser.require(XmlPullParser.START_TAG, ns, "rss");
        NewsModel newsModel = null;
        int event = parser.getEventType();
        String tagName = "";
        String text = "";
        while (event != XmlPullParser.END_DOCUMENT) {
            tagName = parser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    if (tagName.equals("item")) {
                        newsModel = new NewsModel();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if (tagName.equals("item")) {
                        newsEntries.add(newsModel);
                    }
                    if (newsModel != null) {
                        readEntry(newsModel, tagName, text);
                    }
                    break;
            }
            event = parser.next();
        }
        return newsEntries;
    }

    private void readEntry(NewsModel model, String tag, String text) throws ParseException {
        switch (tag) {
            case "title":
                model.setTitle(text);
                break;
            case "description":
                model.setDescription(
                        Html.fromHtml(
                                text.replaceAll("\\<.*?\\>", "").trim(),
                                Html.FROM_HTML_MODE_LEGACY
                        ).toString());
                model.setImage(parseImgFromDescription(text));
                break;
            case "dc:creator":
                model.setAuthor(text);
                break;
            case "link":
                model.setLink(text);
                break;
            case "pubDate":
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "E, dd MMM yyyy HH:mm:ss Z",
                        Locale.US);
                model.setPublicationDate(formatter.parse(text));
                break;
            case "dc:identifier":
                model.setUniqueId(text);
                break;
            case "category":
                if (model.getKeywords() == null) {
                    model.setKeywords(new ArrayList<>());
                }
                model.getKeywords().add(text);
                break;
        }
    }

    private String parseImgFromDescription(String text) {
        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

}
