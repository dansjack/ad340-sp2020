package com.example.djackad340;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchCardViewHolder> {
    private static final int LENGTH = 10;

    MatchCardRecyclerViewAdapter() {

    }

    @NonNull
    @Override
    public MatchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        Resources res = parent.getResources();
        ImageButton favBtn = layoutView.findViewById(R.id.favorite_button);
        TextView name = layoutView.findViewById(R.id.matchName);
        favBtn.setOnClickListener(view -> {
            if (favBtn.getTag().equals(Constants.UNLIKED)) {
                StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_liked))
                        .append(name.getText()).append("!");
                favBtn.setImageResource(R.drawable.ic_favorite_bluegray_24dp);
                favBtn.setTag(Constants.LIKED);
                Toast.makeText(parent.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                likeMsg.setLength(0);
            } else {
                StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_unliked))
                        .append(name.getText()).append("!");
                favBtn.setImageResource(R.drawable.ic_favorite_border_bluegray_24dp);
                favBtn.setTag(Constants.UNLIKED);
                Toast.makeText(parent.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                likeMsg.setLength(0);
            }
        });
        return new MatchCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchCardViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();
        String[] mNames = res.getStringArray(R.array.matchNames);
        String[] mDescription = res.getStringArray(R.array.matchDesc);
        TypedArray mImages = res.obtainTypedArray(R.array.matchImages);

        holder.matchName.setText(mNames[position]);
        holder.matchDesc.setText(mDescription[position]);
        holder.matchImage.setImageDrawable(mImages.getDrawable(position));
        mImages.recycle();
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}
