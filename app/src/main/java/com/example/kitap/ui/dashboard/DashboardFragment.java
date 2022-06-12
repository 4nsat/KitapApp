package com.example.kitap.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.MainActivity;
import com.example.kitap.R;
import com.example.kitap.ui.home.BooksItem;
import com.example.kitap.ui.home.BooksListAdapter;
import com.example.kitap.ui.home.books.BooksFragment;
import com.example.kitap.ui.home.viewall.SingleFragment;

import java.util.List;

public class DashboardFragment extends Fragment {
    RecyclerView genreRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);



        DBAssetHelper dbSetup = new DBAssetHelper(getActivity());
        dbSetup.getReadableDatabase();
        genreRecycler = root.findViewById(R.id.genresRecycler);


        GenreDatabase db = new GenreDatabase(getActivity());
        List<GenresItem> genreList = db.getGenreList(); //Genres

        LinearLayoutManager linearNewLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        GenresListAdapter.OnGenreClickListener genreClickListener = new GenresListAdapter.OnGenreClickListener() {
            @Override
            public void onGenreClick(GenresItem genresItem, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("type", "genre");
                bundle.putString("key", genresItem.getgId());

                Fragment fragment = new SingleFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("fragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();

            }
        };

        genreRecycler.setLayoutManager(linearNewLayoutManager);

        genreRecycler.setAdapter(new GenresListAdapter(getContext(),genreList, genreClickListener));

        return root;
    }
}