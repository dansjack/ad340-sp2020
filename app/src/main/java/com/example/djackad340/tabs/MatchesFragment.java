package com.example.djackad340.tabs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.djackad340.Constants;
import com.example.djackad340.MatchCardRecyclerViewAdapter;
import com.example.djackad340.MatchItem;
import com.example.djackad340.OnListFragmentInteractionListener;
import com.example.djackad340.R;

import java.util.List;


public class MatchesFragment extends Fragment {
    private static final String TAG = MatchesFragment.class.getName();
    private List<MatchItem> mMatches;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private MatchCardRecyclerViewAdapter rcvAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMatches = getArguments().getParcelableArrayList(Constants.MATCHES);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMatches = getArguments().getParcelableArrayList(Constants.MATCHES);
        recyclerView.setAdapter(new MatchCardRecyclerViewAdapter(mMatches, mListener));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        rcvAdapter = new MatchCardRecyclerViewAdapter(mMatches, mListener);

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rcvAdapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnListFragmentInteractionListener) context;
    }
}
