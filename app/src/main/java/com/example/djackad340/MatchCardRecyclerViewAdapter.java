package com.example.djackad340;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
