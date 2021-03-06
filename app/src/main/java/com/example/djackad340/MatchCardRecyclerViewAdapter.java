package com.example.djackad340;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.djackad340.tabs.MatchesFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchCardViewHolder> {
    private List<MatchItem> matches;
    private OnListFragmentInteractionListener listener;

    private static final String TAG = MatchCardRecyclerViewAdapter.class.getName();


    public MatchCardRecyclerViewAdapter(List<MatchItem> mMatches, OnListFragmentInteractionListener mListener) {
        matches = mMatches;
        listener = mListener;
    }

    @NonNull
    @Override
    public MatchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        return new MatchCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchCardViewHolder holder, int position) {
        if (matches != null && position < matches.size()) {
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
                    } else {
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

    }

    @Override
    public int getItemCount() {
        if (matches != null) {
            return matches.size();
        }
        return 0;
    }
}
