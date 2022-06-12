package com.example.kitap.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.ImageManager;
import com.example.kitap.R;

import java.util.List;

public class BooksListAdapter extends RecyclerView.Adapter<BooksItemViewHolder> {
    List<BooksItem> mBooksItems;

    public interface OnBookClickListener{
        void onBookClick(BooksItem booksItem, int position);
    }

    private final OnBookClickListener onClickListener;

    public BooksListAdapter(List<BooksItem> booksItems, OnBookClickListener onClickListener) {
        mBooksItems = booksItems;
        this.onClickListener = onClickListener;
    }

    @Override
    public BooksItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new BooksItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksItemViewHolder holder, int position) {
        BooksItem book = mBooksItems.get(position);
        holder.mNameTextView.setText(mBooksItems.get(position).getbName());
        holder.mAuthorTextView.setText(mBooksItems.get(position).getbAuthor());
        String bImg;
        bImg = mBooksItems.get(position).getbImg();
        Log.d("Book Image", "onBindViewHolder: " + bImg);
        if (bImg != null) {
            ImageManager imageManager = new ImageManager();
            imageManager.fetchImage(bImg, holder.mNewImgView);
        } else {
            holder.mNewImgView.setImageResource(R.drawable.openbook);;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onBookClick(book, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooksItems.size();
    }
}
