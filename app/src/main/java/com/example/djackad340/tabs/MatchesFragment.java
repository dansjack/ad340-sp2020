package com.example.djackad340.tabs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.djackad340.Constants;
import com.example.djackad340.MatchCardRecyclerViewAdapter;
import com.example.djackad340.MatchItem;
import com.example.djackad340.R;

import java.util.List;


public class MatchesFragment extends Fragment {
    private static final String TAG = MatchesFragment.class.getName();
    private List<MatchItem> mMatches;
    private MatchesFragment.OnListFragmentInteractionListener mListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMatches = getArguments().getParcelableArrayList(Constants.MATCHES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);


        // Set up RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MatchCardRecyclerViewAdapter(mMatches, mListener));


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnListFragmentInteractionListener) context;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MatchItem item);
    }
}