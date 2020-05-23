package com.example.djackad340;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.djackad340.MatchesFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchCardViewHolder> {
//    private static final int LENGTH = 6;
    private List<MatchItem> matches;
    private OnListFragmentInteractionListener listener;

    private static final String TAG = MatchCardRecyclerViewAdapter.class.getName();


    MatchCardRecyclerViewAdapter(List<MatchItem> mMatches, OnListFragmentInteractionListener mListener) {
        matches = mMatches;
        listener = mListener;
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
                        .append(name.getText()).append(res.getString(R.string.exclamation_mark));
                favBtn.setImageResource(R.drawable.ic_favorite_bluegray_24dp);
                favBtn.setTag(Constants.LIKED);
                Toast.makeText(parent.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                likeMsg.setLength(0);
            } else {
                StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_unliked))
                        .append(name.getText()).append(res.getString(R.string.exclamation_mark));
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
        String[] mDescription = res.getStringArray(R.array.matchDesc);
        MatchItem match = matches.get(position);

        holder.matchName.setText(match.name);
        holder.matchDesc.setText(mDescription[position]);
        Picasso.get().load(match.imageUrl).into(holder.matchImage);

        if (match.liked) {
            holder.matchFavBtn.setImageResource(R.drawable.ic_favorite_bluegray_24dp);
        } else {
            holder.matchFavBtn.setImageResource(R.drawable.ic_favorite_border_bluegray_24dp);
        }

        holder.matchFavBtn.setOnClickListener(view -> {
            if (listener != null) {
                if (match.liked) {
                    holder.matchFavBtn.setImageResource(R.drawable.ic_favorite_border_bluegray_24dp);
                    StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_unliked))
                            .append(holder.matchName.getText())
                            .append(res.getString(R.string.exclamation_mark));

                    Toast.makeText(holder.itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                    likeMsg.setLength(0);
                } else if (!match.liked){
                    holder.matchFavBtn.setImageResource(R.drawable.ic_favorite_bluegray_24dp);
                    StringBuilder likeMsg = new StringBuilder(res.getString(R.string.you_liked))
                            .append(holder.matchName.getText())
                            .append(res.getString(R.string.exclamation_mark));

                    Toast.makeText(holder.itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                    likeMsg.setLength(0);
                }
                listener.onListFragmentInteraction(match);

            }

        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
