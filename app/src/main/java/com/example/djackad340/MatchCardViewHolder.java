package com.example.djackad340;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchCardViewHolder extends RecyclerView.ViewHolder {
    public ImageView matchImage;
    public TextView matchName;
    public TextView matchDesc;


    public MatchCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.matchImage);
        matchName = itemView.findViewById(R.id.matchName);
        matchDesc = itemView.findViewById(R.id.matchDesc);
    }
}
