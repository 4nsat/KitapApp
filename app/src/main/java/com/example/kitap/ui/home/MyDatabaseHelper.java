package com.example.kitap.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.ui.home.books.bDescDBH;
import com.example.kitap.ui.home.books.bGenreDBH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = MyDatabaseHelper.class.getCanonicalName();
    DBAssetHelper dbAssetHelper;
    private Context context;

    private static final String TABLE_NAME = "books";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "bookName";
    private static final String COLUMN_AUTHOR = "bookAuthor";
    private static final String COLUMN_IMG = "img";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_ID_DESC = "idDesc";
    private static final String COLUMN_PAGES = "pages";

    public static final String[] BOOKS_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_AUTHOR, COLUMN_IMG, COLUMN_ID_DESC};

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DBAssetHelper.DATABASE_NAME, null, DBAssetHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_IMG + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    //New books slider
    public List<BooksItem> getBooksList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                BOOKS_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COLUMN_DATE + " DESC LIMIT 10", // g. order by
                null); // h. limit


        List<BooksItem> booksItems = new ArrayList<>();
        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                booksItems.add(new BooksItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return booksItems;
    }

    //Popular books slider
    public List<BooksItem> getPopBooksList() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                BOOKS_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COLUMN_RATING + " DESC LIMIT 10", // g. order by
                null); // h. limit


        List<BooksItem> booksItems = new ArrayList<>();
        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                booksItems.add(new BooksItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return booksItems;
    }

    //Easy books slider
    public List<BooksItem> getEzBooksList() {

        SQLiteDatabase db = this.getReadableDatabase();
        String getPages;

        /*Cursor cursor = db.query(TABLE_NAME + "," +  bDescDBH.TABLE_NAME + "as getPages", // a. table
                BOOKS_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit*/

        Cursor cursor = db.query(TABLE_NAME + " LEFT OUTER JOIN description ON books.idDesc = description._id",
                new String[]{"books._id", "books.bookName", "books.bookAuthor", "books.img", "books.idDesc", "description._id", "description.pages"},
                null,
                null,
                null,
                null,
                "pages ASC LIMIT 10",
                null);


        List<BooksItem> booksItems = new ArrayList<>();
        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Log.d("Cursor EZ", String.valueOf(cursor));
               /* //Description
                String selectionGenre = "_id = ?";
                Log.d("Desc id", "getEzBooksList: " + cursor.getColumnIndex(COLUMN_ID_DESC));
                String[] selectionArgsGenre = new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_ID_DESC))};
                Cursor cursorDesc = db.query(bDescDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionGenre, // c. selections
                        selectionArgsGenre, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorDesc.moveToFirst()) {
                    int idIndexGenre = cursorDesc.getColumnIndex(bDescDBH.COLUMN_ID);
                    int genreIndex = cursorDesc.getColumnIndex(bDescDBH.COLUMN_PAGES);
                    do {
                        Log.d("mLog", "ID = " + cursorDesc.getInt(idIndexGenre));
                        //bGenre.setText(cursorGenre.getString(genreIndex));
                        Log.d("sDesc", "getSingleList: " + cursorDesc.getString(genreIndex));
                        getPages = cursorDesc.getString(genreIndex);
                    } while (cursorDesc.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Description*/


                booksItems.add(new BooksItem(cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMG))));
                cursor.moveToNext();
            }
        }

        return booksItems;
    }

}
