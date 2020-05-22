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
    public String lat;
    public String longitude;
    public String imageUrl;
    public boolean liked;

    public MatchItem() {}

    public MatchItem(String name, String lat, String longitude, String imageUrl, boolean liked) {
        this.name = name;
        this.lat = lat;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.liked = liked;
    }

    public MatchItem(Parcel in) {
        name = in.readString();
        lat = in.readString();
        longitude = in.readString();
        imageUrl = in.readString();
        liked = in.readByte() != 0;
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
        result.put("uid", uid);
        result.put("name", name);
        result.put("lat", lat);
        result.put("longitude", longitude);
        result.put("imageUrl", imageUrl);
        result.put("liked", liked);

        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(lat);
        parcel.writeString(longitude);
        parcel.writeString(imageUrl);
        parcel.writeByte((byte) (liked ? 1 : 0));

    }
}
