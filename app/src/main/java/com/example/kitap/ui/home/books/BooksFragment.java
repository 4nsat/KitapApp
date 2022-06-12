package com.example.kitap.ui.home.books;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitap.ImageManager;
import com.example.kitap.MainActivity;
import com.example.kitap.R;
import com.example.kitap.downloadmanager.DownloadTask;
import com.example.kitap.ui.reader.ReadFragment;
import com.example.kitap.ui.reader.ReaderFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import static android.os.Environment.getDataDirectory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "key";

    private final static int MY_PERMISSION = 1;

    bookDBHelper bDBHelper;
    TextView bName, bAuthor, bGenre, bRating, bDesc, bYear, bPages, bEdition;
    ImageView bImg;
    Button bReadBtn;

    String bURL;
    String file_name;
    File fileExt;
    public static boolean checkBool = false;

    // TODO: Rename and change types of parameters
    private String mParam1;

    public BooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BooksFragment newInstance(String param1, String param2) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bDBHelper = new bookDBHelper(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.d("BooksFRag", "onCreate: " + mParam1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_books, container, false);

        bName = root.findViewById(R.id.bNameBook);
        bAuthor = root.findViewById(R.id.bAuthorBook);
        bGenre = root.findViewById(R.id.bGenreBook);
        bImg = root.findViewById(R.id.bImgBook); //ImgView
        bRating = root.findViewById(R.id.bRatingBook);
        bDesc = root.findViewById(R.id.bDescBook);
        bYear = root.findViewById(R.id.bYearBook);
        bPages = root.findViewById(R.id.bPagesBook);
        bEdition = root.findViewById(R.id.bEditionBook);
        bReadBtn = root.findViewById(R.id.bReadBook); //Button


        SQLiteDatabase db = bDBHelper.getReadableDatabase();
        String selection = "_id = ?";
        String[] selectionArgs = new String[]{String.valueOf(mParam1)};
        Cursor cursor = db.query(bookDBHelper.TABLE_NAME, // a. table
                null, // b. column names
                selection, // c. selections
                selectionArgs, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_NAME);
            int authorIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_AUTHOR);
            int imgIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_IMG);
            int genreIdIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_GENRE_ID);
            int ratingIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_RATING);
            int linkIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_LINK);
            int descIdIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_DESC_ID);
            int dateIndex = cursor.getColumnIndex(bookDBHelper.COLUMN_DATE);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", author = " + cursor.getString(authorIndex));


                bName.setText(cursor.getString(nameIndex));
                bAuthor.setText(cursor.getString(authorIndex));
                ImageManager.fetchImage(cursor.getString(imgIndex), bImg);
                bGenre.setText(cursor.getString(genreIdIndex));
                bRating.setText(cursor.getString(ratingIndex));

                //URL file
                bURL = cursor.getString(linkIndex);
                Log.d("Url FilePDF", "url: " + bURL);

                //Порверка файла
                file_name = bURL.substring(bURL.lastIndexOf("/") + 1);
                fileExt = new File(getActivity().getApplicationContext().getFilesDir().getPath() + "/Kitap/" + file_name);

                if (fileExt.exists()) {
                    bReadBtn.setText("Oqu");
                } else {
                    bReadBtn.setText("Jükteu");
                }

                //Description
                String selectionDesc = "_id = ?";
                String[] selectionArgsDesc = new String[]{String.valueOf(cursor.getString(descIdIndex))};
                Cursor cursorDesc = db.query(bDescDBH.TABLE_NAME, // a. table
                        null, // b. column names
                        selectionDesc, // c. selections
                        selectionArgsDesc, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
                if (cursorDesc.moveToFirst()) {
                    int idIndexDesc = cursorDesc.getColumnIndex(bDescDBH.COLUMN_ID);
                    int descIndexDesc = cursorDesc.getColumnIndex(bDescDBH.COLUMN_DESCRIP);
                    int yearIndexDesc = cursorDesc.getColumnIndex(bDescDBH.COLUMN_YEAR);
                    int pagesIndexDesc = cursorDesc.getColumnIndex(bDescDBH.COLUMN_PAGES);
                    int editionIndexDesc = cursorDesc.getColumnIndex(bDescDBH.COLUMN_EDITION);
                    do {
                        Log.d("mLog", "ID = " + cursorDesc.getInt(idIndexDesc));

                        bDesc.setText(cursorDesc.getString(descIndexDesc));
                        bYear.setText("Şyğarylğan jyly: " + cursorDesc.getString(yearIndexDesc));
                        bPages.setText("Better: " + cursorDesc.getString(pagesIndexDesc));
                        bEdition.setText("Şyğarylym: " + cursorDesc.getString(editionIndexDesc));


                    } while (cursorDesc.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Description

                //Genre
                String selectionGenre = "_id = ?";
                String[] selectionArgsGenre = new String[]{String.valueOf(cursor.getString(genreIdIndex))};
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
                        bGenre.setText(cursorGenre.getString(genreIndex));
                    } while (cursorGenre.moveToNext());
                } else
                    Log.d("mLogDesc", "0 rows");
                //Genre

            } while (cursor.moveToNext());
        } else
            Log.d("mLog", "0 rows");

        cursor.close();

        bReadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isStoragePermissionGranted()) {
                    File dir = new File(getActivity().getApplicationContext().getFilesDir().getPath() + "/Kitap/");
                    Log.d("output Dir", "Path: " + dir.getPath());
                    try {
                        dir.mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity, "Cannot create folder", Toast.LENGTH_SHORT).show();
                        Log.d("Dir", "Cannot create folder");
                    }
                    if (fileExt.exists()) {
                        Log.d("File Exists", "onClick: FileExists");
                        checkBool = true;
                    } else {
                        Log.d("File Exists", "onClick: FileNotExists");
                        new DownloadTask(getActivity()).execute(bURL);
                        Toast.makeText(getContext(), "\"Oqu\" batyrmasyn qaita basyƞyz", Toast.LENGTH_SHORT).show();
                        bReadBtn.setText("Oqu");
                    }

                    if (checkBool) {
                        Bundle bundle = new Bundle();
                        bundle.putString("fileName", file_name);

                        Fragment fragment = new ReadFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                        transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                        fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                        transaction.addToBackStack(null); // Добавляете в backstack, чтобы можно было вернутся обратно

                        transaction.commit();
                    }

                    //Toast.makeText(getContext(), "Folder created.", Toast.LENGTH_SHORT).show();

                }

            }
        });
        return root;
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

}
