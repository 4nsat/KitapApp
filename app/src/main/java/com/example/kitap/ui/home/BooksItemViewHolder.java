package com.example.kitap.ui.home;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.R;

public class BooksItemViewHolder extends RecyclerView.ViewHolder {
    public TextView mNameTextView;
    public TextView mAuthorTextView;
    public ImageView mNewImgView;

    LinearLayout newLayout;

    public BooksItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mNameTextView = (TextView)itemView.findViewById(R.id.bookName);
        mAuthorTextView = (TextView)itemView.findViewById(R.id.bookAuthor);
        mNewImgView = itemView.findViewById(R.id.bookImg);

        newLayout = itemView.findViewById(R.id.newLayout);
    }
}
