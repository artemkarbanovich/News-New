package karbanovich.fit.bstu.newsnew.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseBuilder extends SQLiteOpenHelper {

    private static final int SCHEMA = 1;
    private static final String DATABASE_NAME = "NEWSNEWDB";
    private static final String NEWS_TABLE = "NEWS_HEADLINES";

    public DatabaseBuilder(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NEWS_TABLE + "(                    "
                    + " ID integer primary key autoincrement not null,  "
                    + " CATEGORY text,                                  "
                    + " COUNTRY_CODE text,                              "
                    + " SOURCE text,                                    "
                    + " AUTHOR text,                                    "
                    + " TITLE text,                                     "
                    + " DESCRIPTION text,                               "
                    + " URL text,                                       "
                    + " URL_TO_IMAGE text,                              "
                    + " PUBLISHED_AT text,                              "
                    + " CONTENT text                                  );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + NEWS_TABLE);
        onCreate(db);
    }
}
