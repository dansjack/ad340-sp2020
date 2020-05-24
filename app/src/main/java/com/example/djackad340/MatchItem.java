package com.example.djackad340;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class MatchItem implements Parcelable {
    public String uid;
    public String name;
    public String imageUrl;
    public boolean liked;
//    public String lat;
//    public String longitude;

    public MatchItem() {}

    public MatchItem(String name, String lat, String longitude, String imageUrl, boolean liked) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.liked = liked;
//        this.lat = lat;
//        this.longitude = longitude;
    }

    public MatchItem(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        liked = in.readByte() != 0;
//        lat = in.readString();
//        longitude = in.readString();
    }

    public static final Creator<MatchItem> CREATOR = new Creator<MatchItem>() {
        @Override
        public MatchItem createFromParcel(Parcel in) {
            return new MatchItem(in);
        }

        @Override
        public MatchItem[] newArray(int size) {
            return new MatchItem[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constants.UID, uid);
        result.put(Constants.NAME, name);
        result.put(Constants.IMAGE_URL, imageUrl);
        result.put(Constants.LIKED, liked);
//        result.put("longitude", longitude);
//        result.put("lat", lat);

        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeByte((byte) (liked ? 1 : 0));
//        parcel.writeString(lat);
//        parcel.writeString(longitude);

    }
}
