package com.example.kitap.ui.dashboard;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.MainActivity;
import com.example.kitap.R;

import java.util.List;



public class GenresListAdapter extends RecyclerView.Adapter<GenresItemViewHolder> {
    List<GenresItem> mGenresItems;

    Context context;
    public interface OnGenreClickListener {
        void onGenreClick(GenresItem genresItem, int position);

    }

    private final GenresListAdapter.OnGenreClickListener onClickListener;

    public GenresListAdapter(Context context, List<GenresItem> genresItems, GenresListAdapter.OnGenreClickListener onClickListener) {
        mGenresItems = genresItems;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public GenresItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false);
        return new GenresItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenresItemViewHolder holder, int position) {
        GenresItem genreId = mGenresItems.get(position);
        holder.genreTextView.setText(mGenresItems.get(position).getGenre());
        String gImg = mGenresItems.get(position).getGenreIc();
        Resources res = context.getResources();
        int resID = res.getIdentifier(gImg , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID );

        holder.genreImgView.setImageDrawable(drawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onGenreClick(genreId, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGenresItems.size();
    }
}