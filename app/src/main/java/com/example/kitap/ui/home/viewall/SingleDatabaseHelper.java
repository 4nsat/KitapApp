package com.example.kitap.ui.home.viewall;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.ui.home.BooksItem;
import com.example.kitap.ui.home.books.bGenreDBH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SingleDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "books";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "bookName";
    private static final String COLUMN_AUTHOR = "bookAuthor";
    private static final String COLUMN_ID_GENRE = "idGenre";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_IMG = "img";
    private static final String COLUMN_DATE = "date";


    public static final String[] BOOKS_COLUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_AUTHOR,COLUMN_ID_GENRE,COLUMN_RATING,COLUMN_IMG};

    public SingleDatabaseHelper(@Nullable Context context) {
        super(context, DBAssetHelper.DATABASE_NAME, null, DBAssetHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_ID_GENRE + " INTEGER, " +
                COLUMN_RATING + " TEXT," +
                COLUMN_IMG + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public List<SingleItem> getSingleList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                BOOKS_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COLUMN_DATE  + " DESC LIMIT 25", // g. order by
                null); // h. limit


        List<SingleItem> singleItems = new ArrayList<>();
        int i = 1;
        String getGenre = "joq";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                //Genre
                String selectionGenre = "_id = ?";
                String[] selectionArgsGenre = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_ID_GENRE))};
                Cursor cursorGenre = db.query(bGenreDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionGenre, // c. selections
                        selectionArgsGenre, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorGenre.moveToFirst()) {
                    int idIndexGenre = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_ID);
                    int genreIndex = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_GENRE);
                    do {
                        Log.d("mLog", "ID = " + cursorGenre.getInt(idIndexGenre));
                        //bGenre.setText(cursorGenre.getString(genreIndex));
                        Log.d("sGenre", "getSingleList: " + cursorGenre.getString(genreIndex));
                        getGenre = cursorGenre.getString(genreIndex);
                    } while (cursorGenre.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Genre

                singleItems.add(new SingleItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        getGenre,
                        cursor.getString(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }

        return singleItems;
    }

    public List<SingleItem> getPopSingleList() { //Popular ViewAll

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                BOOKS_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COLUMN_RATING  + " DESC LIMIT 25", // g. order by
                null); // h. limit


        List<SingleItem> singleItems = new ArrayList<>();
        int i = 1;
        String getGenre = "joq";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                //Genre
                String selectionGenre = "_id = ?";
                String[] selectionArgsGenre = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_ID_GENRE))};
                Cursor cursorGenre = db.query(bGenreDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionGenre, // c. selections
                        selectionArgsGenre, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorGenre.moveToFirst()) {
                    int idIndexGenre = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_ID);
                    int genreIndex = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_GENRE);
                    do {
                        Log.d("mLog", "ID = " + cursorGenre.getInt(idIndexGenre));
                        //bGenre.setText(cursorGenre.getString(genreIndex));
                        Log.d("sGenre", "getSingleList: " + cursorGenre.getString(genreIndex));
                        getGenre = cursorGenre.getString(genreIndex);
                    } while (cursorGenre.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Genre

                singleItems.add(new SingleItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        getGenre,
                        cursor.getString(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }

        return singleItems;
    }

    public List<SingleItem> getEzSingleList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME + " LEFT OUTER JOIN description ON books.idDesc = description._id",
                new String[]{"books._id", "books.bookName", "books.bookAuthor", "books.img", "books.idDesc", "books.idGenre", "books.rating", "description._id", "description.pages"},
                null,
                null,
                null,
                null,
                "pages ASC LIMIT 25",
                null); // h. limit


        List<SingleItem> singleItems = new ArrayList<>();
        int i = 1;
        String getGenre = "joq";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                //Genre
                String selectionGenre = "_id = ?";
                String[] selectionArgsGenre = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_ID_GENRE))};
                Cursor cursorGenre = db.query(bGenreDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionGenre, // c. selections
                        selectionArgsGenre, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorGenre.moveToFirst()) {
                    int idIndexGenre = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_ID);
                    int genreIndex = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_GENRE);
                    do {
                        Log.d("mLog", "ID = " + cursorGenre.getInt(idIndexGenre));
                        //bGenre.setText(cursorGenre.getString(genreIndex));
                        Log.d("sGenre", "getSingleList: " + cursorGenre.getString(genreIndex));
                        getGenre = cursorGenre.getString(genreIndex);
                    } while (cursorGenre.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Genre

                singleItems.add(new SingleItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        getGenre,
                        cursor.getString(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }

        return singleItems;
    }

    public List<SingleItem> getGenreSingleList(String id) { //Genre ViewAll

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                BOOKS_COLUMNS, // b. column names
                COLUMN_ID_GENRE + " LIKE " + id , // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit


        List<SingleItem> singleItems = new ArrayList<>();
        int i = 1;
        String getGenre = "joq";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                //Genre
                String selectionGenre = "_id = ?";
                String[] selectionArgsGenre = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_ID_GENRE))};
                Cursor cursorGenre = db.query(bGenreDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionGenre, // c. selections
                        selectionArgsGenre, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorGenre.moveToFirst()) {
                    int idIndexGenre = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_ID);
                    int genreIndex = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_GENRE);
                    do {
                        Log.d("mLog", "ID = " + cursorGenre.getInt(idIndexGenre));
                        //bGenre.setText(cursorGenre.getString(genreIndex));
                        Log.d("sGenre", "getSingleList: " + cursorGenre.getString(genreIndex));
                        getGenre = cursorGenre.getString(genreIndex);
                    } while (cursorGenre.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Genre

                singleItems.add(new SingleItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        getGenre,
                        cursor.getString(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }

        return singleItems;
    }

    public List<SingleItem> getSearchSingleList(String getSearch) { //Genre ViewAll

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                BOOKS_COLUMNS, // b. column names
                COLUMN_NAME + " LIKE '%" + getSearch + "%' or " + COLUMN_AUTHOR + " LIKE '%" + getSearch + "%'", // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit


        List<SingleItem> singleItems = new ArrayList<>();
        int i = 1;
        String getGenre = "joq";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                //Genre
                String selectionGenre = "_id = ?";
                String[] selectionArgsGenre = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_ID_GENRE))};
                Cursor cursorGenre = db.query(bGenreDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionGenre, // c. selections
                        selectionArgsGenre, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorGenre.moveToFirst()) {
                    int idIndexGenre = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_ID);
                    int genreIndex = cursorGenre.getColumnIndex(bGenreDBH.COLUMN_GENRE);
                    do {
                        Log.d("mLog", "ID = " + cursorGenre.getInt(idIndexGenre));
                        //bGenre.setText(cursorGenre.getString(genreIndex));
                        Log.d("sGenre", "getSingleList: " + cursorGenre.getString(genreIndex));
                        getGenre = cursorGenre.getString(genreIndex);
                    } while (cursorGenre.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Genre

                singleItems.add(new SingleItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        getGenre,
                        cursor.getString(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }

        return singleItems;
    }

}
