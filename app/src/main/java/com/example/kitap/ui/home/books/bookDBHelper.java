package com.example.kitap.ui.home.books;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kitap.DBAssetHelper;

public class bookDBHelper extends SQLiteOpenHelper {

    private static final String TAG = bookDBHelper.class.getCanonicalName();

    private Context context;

    public static final String TABLE_NAME = "books";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "bookName";
    public static final String COLUMN_AUTHOR = "bookAuthor";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_GENRE_ID = "idGenre";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_DESC_ID = "idDesc";
    public static final String COLUMN_DATE = "date";


    public bookDBHelper(@Nullable Context context) {
        super(context, DBAssetHelper.DATABASE_NAME, null, DBAssetHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_IMG + " TEXT," +
                COLUMN_GENRE_ID + " INTEGER," +
                COLUMN_RATING + " TEXT," +
                COLUMN_LINK + " TEXT," +
                COLUMN_DESC_ID + " INTEGER," +
                COLUMN_DATE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
}
