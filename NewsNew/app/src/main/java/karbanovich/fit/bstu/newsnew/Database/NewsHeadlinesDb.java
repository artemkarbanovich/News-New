package karbanovich.fit.bstu.newsnew.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import karbanovich.fit.bstu.newsnew.Model.GeolocationState;
import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;
import karbanovich.fit.bstu.newsnew.Model.Source;

public class NewsHeadlinesDb {

    private static final String NEWS_TABLE = "NEWS_HEADLINES";


    public static long getCountOfRowsByCategory(SQLiteDatabase db, String category) {
        Cursor cursor = db.rawQuery(
                "select * from " + NEWS_TABLE  + " where CATEGORY = '" + category + "';",
                null);

        return cursor.getCount();
    }

    public static void deleteRowsByCategory(SQLiteDatabase db, String category) {
        db.execSQL("delete from " + NEWS_TABLE + " where CATEGORY = '" + category + "';");
    }

    public static void addList(SQLiteDatabase db, List<NewsHeadlines> list, String category) {
        for (NewsHeadlines n: list) {
            ContentValues values = new ContentValues();

            values.put("CATEGORY", category);
            values.put("COUNTRY_CODE", GeolocationState.getInstance().getCountryCode());
            values.put("SOURCE", n.getSource().getName());
            values.put("AUTHOR", n.getAuthor());
            values.put("TITLE", n.getTitle());
            values.put("DESCRIPTION", n.getDescription());
            values.put("URL", n.getUrl());
            values.put("URL_TO_IMAGE", n.getUrlToImage());
            values.put("PUBLISHED_AT", n.getPublishedAt());
            values.put("CONTENT", n.getContent());

            db.insert(NEWS_TABLE, null, values);
        }
    }

    public static List<NewsHeadlines> getNewsByCategory(SQLiteDatabase db, String category) {
        List<NewsHeadlines> headlines = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "select * from " + NEWS_TABLE + " where CATEGORY = '" + category + "';",
                null);

        while (cursor.moveToNext()) {
            NewsHeadlines n = new NewsHeadlines();
            Source s = new Source();

            s.setName(cursor.getString(cursor.getColumnIndexOrThrow("SOURCE")));
            n.setSource(s);
            n.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow("AUTHOR")));
            n.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("TITLE")));
            n.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION")));
            n.setUrl(cursor.getString(cursor.getColumnIndexOrThrow("URL")));
            n.setUrlToImage(cursor.getString(cursor.getColumnIndexOrThrow("URL_TO_IMAGE")));
            n.setPublishedAt(cursor.getString(cursor.getColumnIndexOrThrow("PUBLISHED_AT")));
            n.setContent(cursor.getString(cursor.getColumnIndexOrThrow("CONTENT")));

            headlines.add(n);
        }
        cursor.close();

        return headlines;
    }
}
