package com.example.kitap.ui.home.books;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kitap.DBAssetHelper;

public class bDescDBH extends SQLiteOpenHelper {

    private static final String TAG = bDescDBH.class.getCanonicalName();

    private Context context;

    public static final String TABLE_NAME = "description";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESCRIP = "descrip";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PAGES = "pages";
    public static final String COLUMN_EDITION = "edition";


    public bDescDBH(@Nullable Context context) {
        super(context, DBAssetHelper.DATABASE_NAME, null, DBAssetHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCRIP + " TEXT, " +
                COLUMN_YEAR + " TEXT, " +
                COLUMN_PAGES + " TEXT," +
                COLUMN_EDITION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
}
