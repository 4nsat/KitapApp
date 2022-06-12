package com.example.kitap.ui.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.R;

public class GenresItemViewHolder extends RecyclerView.ViewHolder {
    public TextView genreTextView;
    public ImageView genreImgView;

    LinearLayout genreLayout;

    public GenresItemViewHolder(@NonNull View itemView) {
        super(itemView);
        genreTextView = (TextView)itemView.findViewById(R.id.genreTxt);
        genreImgView = itemView.findViewById(R.id.genreImg);

        genreLayout = itemView.findViewById(R.id.genreLayout);
    }
}
