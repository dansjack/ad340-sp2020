package com.example.djackad340.tabs;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.djackad340.Constants;
import com.example.djackad340.R;


public class ProfileFragment extends Fragment {
    private static final String TAG = ProfileFragment.class.getSimpleName();


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView profName = view.findViewById(R.id.profileName);
        TextView profAgeLoc = view.findViewById(R.id.profileAgeLoc);
        TextView profOcc = view.findViewById(R.id.profileOcc);
        TextView profDesc = view.findViewById(R.id.profileDesc);
        ImageView profilePic = view.findViewById(R.id.profilePic);

        Bundle bundle = getArguments();

        StringBuilder nameString = new StringBuilder(bundle.getString(Constants.KEY_FNAME).trim());
        StringBuilder ageLocString = new StringBuilder(bundle.getString(Constants.KEY_AGE))
                .append(", ").append(bundle.getString(Constants.KEY_LOC).trim());
        StringBuilder occString = new StringBuilder(" ")
                .append(bundle.getString(Constants.KEY_OCC).trim());
        StringBuilder descString = new StringBuilder(bundle.getString(Constants.KEY_DESC).trim());

        profilePic.setImageURI((Uri) bundle.get(Constants.KEY_Uri));
        profName.setText(nameString);
        profAgeLoc.setText(ageLocString);
        profOcc.setText(occString);
        profDesc.setText(descString);
        return view;
    }
}
