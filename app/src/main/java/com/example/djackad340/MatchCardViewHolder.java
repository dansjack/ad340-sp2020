package com.example.djackad340;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MatchCardViewHolder extends RecyclerView.ViewHolder {
    ImageView matchImage;
    TextView matchName;
    TextView matchDesc;
    ImageView matchFavBtn;



    MatchCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.matchImage);
        matchName = itemView.findViewById(R.id.matchName);
        matchDesc = itemView.findViewById(R.id.matchDesc);
        matchFavBtn = itemView.findViewById(R.id.favorite_button);

    }
}
