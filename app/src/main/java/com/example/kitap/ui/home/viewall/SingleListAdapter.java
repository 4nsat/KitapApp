package com.example.kitap.ui.home.viewall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.ImageManager;
import com.example.kitap.R;

import java.util.List;

public class SingleListAdapter extends RecyclerView.Adapter<SingleItemViewHolder>{

    public OnSBookClickListener bookSClickListener;
    List<SingleItem> sBooksItems;

    public interface OnSBookClickListener{
        void onSBookClick(SingleItem singleItem, int position);
    }


    private final OnSBookClickListener onClickListener;

    public SingleListAdapter(List<SingleItem> singleItems, OnSBookClickListener onClickListener) {
        sBooksItems = singleItems;
        this.onClickListener = onClickListener;
    }

    @Override
    public SingleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_book, parent, false);
        return new SingleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleItemViewHolder holder, int position) {
        SingleItem singleB = sBooksItems.get(position);
        holder.sNameTextView.setText(sBooksItems.get(position).getsName());
        holder.sAuthorTextView.setText(sBooksItems.get(position).getsAuthor());
        holder.sGenreTextView.setText(sBooksItems.get(position).getsGenre());
        holder.sRatingTextView.setText(sBooksItems.get(position).getsRating());
        String bImg;
        bImg = sBooksItems.get(position).getsImg();
        ImageManager imageManager = new ImageManager();
        imageManager.fetchImage(bImg, holder.sNewImgView);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onSBookClick(singleB, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sBooksItems.size();
    }
}
