package com.example.djackad340;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MatchCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchCardViewHolder> {
    private static final int LENGTH = 10;
    private List<MatchItemModel> matchItemModels;

    MatchCardRecyclerViewAdapter(List<MatchItemModel> matchItemModels) {
        this.matchItemModels = matchItemModels;
    }

    @NonNull
    @Override
    public MatchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_card, parent, false);
        return new MatchCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchCardViewHolder holder, int position) {
        MatchItemModel matchItemModel = matchItemModels.get(position);

        holder.matchName.setText(matchItemModel.getName());
        holder.matchDesc.setText(matchItemModel.getDescription());
        holder.matchImage.setImageDrawable(matchItemModel.getImage());
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}
