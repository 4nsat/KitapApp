package com.example.kitap.ui.dashboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.ui.home.BooksItem;
import com.example.kitap.ui.home.books.bGenreDBH;

import java.util.ArrayList;
import java.util.List;

public class GenreDatabase extends SQLiteOpenHelper {

    private static final String TAG = bGenreDBH.class.getCanonicalName();

    private Context context;

    public static final String TABLE_NAME = "genres";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_GENRE_IC = "genreIc";


    public static final String[] GENRE_COLUMNS = {COLUMN_ID, COLUMN_GENRE, COLUMN_GENRE_IC};

    public GenreDatabase(@Nullable Context context) {
        super(context, DBAssetHelper.DATABASE_NAME, null, DBAssetHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GENRE + " TEXT, " +
                COLUMN_GENRE_IC + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


    //Genres view

    public List<GenresItem> getGenreList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                GENRE_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit


        List<GenresItem> genreItems = new ArrayList<>();
        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                genreItems.add(new GenresItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_GENRE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_GENRE_IC))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return genreItems;
    }
}
