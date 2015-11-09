package com.technical.saion.newsapp.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.technical.saion.newsapp.model.NewsItem;
import com.technical.saion.newsapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saion on 09.11.2015.
 */
public class NewsTable {

    public final static Uri URI= Constants.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();

    public static void save (Context context,@NonNull List<NewsItem> newsItems)
    {
        ContentValues[] values=new ContentValues[newsItems.size()];
        for (int i=0;i<newsItems.size();i++)
        {
            values[i]=toContentValues(newsItems.get(i));
        }
        context.getContentResolver().bulkInsert(URI,values);
    }

    @NonNull
    public static ContentValues toContentValues(@NonNull NewsItem newsItem)
    {
        ContentValues values = new ContentValues();
        values.put(Columns.TITLE, newsItem.getTitle());
        values.put(Columns.DATE, newsItem.getDate());
        values.put(Columns.LINK, newsItem.getImageLink());
        values.put(Columns.DESC, newsItem.getDesc());
        values.put(Columns.WEB, newsItem.getWebURL());
        return values;
    }

    public static NewsItem fromCursor(Cursor cursor)
    {
        String title = cursor.getString(cursor.getColumnIndex(Columns.TITLE));
        String imageLink = cursor.getString(cursor.getColumnIndex(Columns.LINK));
        String desc = cursor.getString(cursor.getColumnIndex(Columns.DESC));
        String date = cursor.getString(cursor.getColumnIndex(Columns.DATE));
        String web = cursor.getString(cursor.getColumnIndex(Columns.WEB));
        return new NewsItem(title,desc, date,imageLink,web);
    }

    @NonNull
    public static ArrayList<NewsItem> listFromCursor(@NonNull Cursor cursor) {
        ArrayList<NewsItem> news = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return news;
        }
        try {
            do {
                news.add(fromCursor(cursor));
            } while (cursor.moveToNext());
            return news;
        } finally {
            cursor.close();
        }
    }

    public static void clear(Context context) {
        context.getContentResolver().delete(URI, null, null);
    }

    public interface Columns {
        String TITLE = "title";
        String LINK = "link";
        String DESC = "desc";
        String DATE = "date";
        String WEB="web";
    }


    public interface Requests {

        String TABLE_NAME = NewsTable.class.getSimpleName();

        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.TITLE + " TEXT NOT NULL, " +
                Columns.DESC + " TEXT NOT NULL, " +
                Columns.LINK + " TEXT NOT NULL , " +
                Columns.WEB + " TEXT NOT NULL , " +
                Columns.DATE + " TEXT NOT NULL" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}
