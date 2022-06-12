package com.example.kitap.ui.home.viewall;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.MainActivity;
import com.example.kitap.R;
import com.example.kitap.ui.home.BooksItem;
import com.example.kitap.ui.home.BooksListAdapter;
import com.example.kitap.ui.home.MyDatabaseHelper;
import com.example.kitap.ui.home.books.BooksFragment;
import com.example.kitap.ui.home.books.bGenreDBH;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleFragment extends Fragment {
    RecyclerView singleRecView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "type";
    private static final String ARG_PARAM2 = "key";

    // TODO: Rename and change types of parameters
    private String mParam;
    private String mParam2;

    public SingleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleFragment newInstance(String param1, String param2) {
        SingleFragment fragment = new SingleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_single, container, false);


        DBAssetHelper dbSetup = new DBAssetHelper(getActivity());
        dbSetup.getReadableDatabase();
        singleRecView = root.findViewById(R.id.singleRecView);


        // определяем слушателя нажатия элемента в списке
        SingleListAdapter.OnSBookClickListener bookSClickListener = new SingleListAdapter.OnSBookClickListener() {
            @Override
            public void onSBookClick(SingleItem singleItem, int position) {

                /*Toast.makeText(getContext(), "Был выбран пункт " + singleItem.getsId(),
                       Toast.LENGTH_SHORT).show();*/

                Bundle bundle = new Bundle();
                bundle.putString("key", singleItem.getsId());

                Fragment fragment = new BooksFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("fragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();
            }
        };
        // создаем адаптер

        SingleDatabaseHelper db = new SingleDatabaseHelper(getActivity());
        List<SingleItem> singleList = db.getSingleList();
        if (mParam == "new") {
            singleList = db.getSingleList();
        } else if (mParam == "pop"){
            singleList = db.getPopSingleList();
        } else if (mParam == "easy"){
            singleList = db.getEzSingleList();
        } else if (mParam == "genre"){
            singleList = db.getGenreSingleList(mParam2);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        singleRecView.setLayoutManager(linearLayoutManager);
        singleRecView.setAdapter(new SingleListAdapter(singleList, bookSClickListener));

        return root;
    }
}