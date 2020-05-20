package com.example.djackad340;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MatchCardViewHolder extends RecyclerView.ViewHolder {
    ImageView matchImage;
    TextView matchName;
    TextView matchDesc;
    private ImageView matchFavBtn;



    MatchCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.matchImage);
        matchName = itemView.findViewById(R.id.matchName);
        matchDesc = itemView.findViewById(R.id.matchDesc);
        matchFavBtn = itemView.findViewById(R.id.favorite_button);
        Resources res = itemView.getResources();

        matchFavBtn.setOnClickListener(view -> {
            if (matchFavBtn.getTag().equals(Constants.UNLIKED)) {
                StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_liked))
                        .append(matchName.getText()).append(res.getString(R.string.exclamation_mark));
                matchFavBtn.setImageResource(R.drawable.ic_favorite_bluegray_24dp);
                matchFavBtn.setTag(Constants.LIKED);
                Toast.makeText(itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                likeMsg.setLength(0);
            } else {
                StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_unliked))
                        .append(matchName.getText()).append(res.getString(R.string.exclamation_mark));
                matchFavBtn.setImageResource(R.drawable.ic_favorite_border_bluegray_24dp);
                matchFavBtn.setTag(Constants.UNLIKED);
                Toast.makeText(itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                likeMsg.setLength(0);
            }
        });

    }
}
