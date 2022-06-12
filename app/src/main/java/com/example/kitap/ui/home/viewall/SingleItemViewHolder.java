package com.example.kitap.ui.home.viewall;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitap.R;

public class SingleItemViewHolder extends RecyclerView.ViewHolder {
    public TextView sNameTextView;
    public TextView sAuthorTextView;
    public TextView sGenreTextView;
    public TextView sRatingTextView;
    public ImageView sNewImgView;

    LinearLayout singleLayout;

    public SingleItemViewHolder(@NonNull View itemView) {
        super(itemView);
        sNameTextView = (TextView)itemView.findViewById(R.id.sNameBook);
        sAuthorTextView = (TextView)itemView.findViewById(R.id.sAuthorBook);
        sGenreTextView = (TextView)itemView.findViewById(R.id.sGenreBook);
        sRatingTextView = (TextView)itemView.findViewById(R.id.sRatingBook);
        sNewImgView = itemView.findViewById(R.id.sImgBook);

        singleLayout = itemView.findViewById(R.id.singleLayout);
    }
}
