package com.example.djackad340;


import android.content.res.TypedArray;
import java.util.ArrayList;
import java.util.List;

class MatchItemViewModel {
    private List<MatchItemModel> matchItems;


    MatchItemViewModel(MatchesFragment context) {
        matchItems = new ArrayList<>();

        String[] mNames = context.getResources().getStringArray(R.array.matchNames);
        String[] mDescription = context.getResources().getStringArray(R.array.matchDesc);
        TypedArray mImages = context.getResources().obtainTypedArray(R.array.matchImages);

        for (int i = 0; i < mNames.length; i++) {
            matchItems.add(new MatchItemModel(mNames[i], mDescription[i], mImages.getDrawable(i)));
        }
        mImages.recycle();
    }

    List<MatchItemModel> getMatchItems() { return matchItems; }
}
