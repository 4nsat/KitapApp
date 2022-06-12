package com.example.kitap.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.MainActivity;
import com.example.kitap.R;
import com.example.kitap.ui.home.books.BooksFragment;
import com.example.kitap.ui.home.viewall.SingleFragment;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView, recyclerPopView, recyclerEzView;
    TextView viewNewAll, viewPopAll, viewEzAll;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        DBAssetHelper dbSetup = new DBAssetHelper(getActivity());
        dbSetup.getReadableDatabase();
        recyclerView = root.findViewById(R.id.new_recycler);
        recyclerPopView = root.findViewById(R.id.pop_recycler);
        recyclerEzView = root.findViewById(R.id.ez_recycler);
        // определяем слушателя нажатия элемента в списке
        BooksListAdapter.OnBookClickListener bookClickListener = new BooksListAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(BooksItem booksItem, int position) {

                /*Toast.makeText(getContext(), "Был выбран пункт " + booksItem.getbId(),
                        Toast.LENGTH_SHORT).show();*/

                Bundle bundle = new Bundle();
                bundle.putString("key", booksItem.getbId());

                Fragment fragment = new BooksFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//Animation maybe
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("NotificationsFragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();
            }
        };
        // создаем адаптер

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<BooksItem> booksList = db.getBooksList(); //New books
        List<BooksItem> popList = db.getPopBooksList(); //Popular books
        List<BooksItem> ezList = db.getEzBooksList(); //Easy books


        LinearLayoutManager linearNewLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearPopLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearEzLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearNewLayoutManager);
        recyclerPopView.setLayoutManager(linearPopLayoutManager);
        recyclerEzView.setLayoutManager(linearEzLayoutManager);

        recyclerView.setAdapter(new BooksListAdapter(booksList, bookClickListener));
        recyclerPopView.setAdapter(new BooksListAdapter(popList, bookClickListener));
        recyclerEzView.setAdapter(new BooksListAdapter(ezList, bookClickListener));


        viewNewAll = root.findViewById(R.id.viewNewAll); // Button New Bari
        viewPopAll = root.findViewById(R.id.viewPopAll); // Button Pop Bari
        viewEzAll = root.findViewById(R.id.viewEzAll); // Button Easy Bari

        viewNewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("type", "new");

                Fragment fragment = new SingleFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("fragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();
            }
        });

        viewPopAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("type", "pop");

                Fragment fragment = new SingleFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("fragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();
            }
        });

        viewEzAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("type", "easy");

                Fragment fragment = new SingleFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("fragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();
            }
        });


        return root;
    }
}