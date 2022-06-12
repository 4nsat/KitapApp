package com.example.kitap.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.DBAssetHelper;
import com.example.kitap.MainActivity;
import com.example.kitap.R;
import com.example.kitap.ui.dashboard.GenresItem;
import com.example.kitap.ui.home.books.BooksFragment;
import com.example.kitap.ui.home.viewall.SingleDatabaseHelper;
import com.example.kitap.ui.home.viewall.SingleItem;
import com.example.kitap.ui.home.viewall.SingleListAdapter;

import java.util.List;

public class NotificationsFragment extends Fragment {

    EditText searchPlain;
    TextView msgTxt;
    RecyclerView searchRecycler;
    FrameLayout matchFrame;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        searchPlain = root.findViewById(R.id.searchPlain);
        searchRecycler = root.findViewById(R.id.searchRecycler);
        matchFrame = root.findViewById(R.id.matchFrame);
        ViewGroup.LayoutParams matchFrgPar = matchFrame.getLayoutParams();
        //ViewGroup.LayoutParams centerFrgPar = centerFrame.getLayoutParams();

        DBAssetHelper dbSetup = new DBAssetHelper(getActivity());
        dbSetup.getReadableDatabase();
        SingleDatabaseHelper db = new SingleDatabaseHelper(getActivity());

        LinearLayoutManager linearNewLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        searchRecycler.setLayoutManager(linearNewLayoutManager);


        // определяем слушателя нажатия элемента в списке
        SingleListAdapter.OnSBookClickListener bookSClickListener = new SingleListAdapter.OnSBookClickListener() {
            @Override
            public void onSBookClick(SingleItem singleItem, int position) {

                /*Toast.makeText(getContext(), "Был выбран пункт " + singleItem.getsId(),
                        Toast.LENGTH_SHORT).show();*/
                //

                Bundle bundle = new Bundle();
                bundle.putString("key", singleItem.getsId());

                Fragment fragment = new BooksFragment(); // Фрагмент, которым собираетесь заменить первый фрагмент
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // Или getSupportFragmentManager(), если используете support.v4
                transaction.add(R.id.nav_host_fragment, fragment, MainActivity.TAG_FRAGMENT);
                fragment.setArguments(bundle);// Заменяете вторым фрагментом. Т.е. вместо метода `add()`, используете метод `replace()`
                transaction.addToBackStack("NotificationsFragment"); // Добавляете в backstack, чтобы можно было вернутся обратно

                transaction.commit();
            }
        };

        searchPlain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //msgTxt.setText(searchPlain.getText()); searchTxt.toString()

                //Params Frame
                matchFrgPar.height = FrameLayout.LayoutParams.WRAP_CONTENT;
                matchFrame.setLayoutParams(matchFrgPar);


                Editable searchTxt = searchPlain.getText();
                List<SingleItem> singleList = db.getSearchSingleList(searchTxt.toString());
                searchRecycler.setAdapter(new SingleListAdapter(singleList, bookSClickListener));

                Log.d("Search", "onTextChanged: " + searchTxt.toString().length());

                if (searchTxt.toString().length() < 2) {
                    singleList.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //msgTxt.setText(R.string.izdeyBeti);
            }
        });


        return root;
    }
}