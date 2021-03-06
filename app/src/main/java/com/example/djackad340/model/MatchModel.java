package com.example.djackad340.model;

import com.example.djackad340.Constants;
import com.example.djackad340.MatchItem;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MatchModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void getMatchItems(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection(Constants.MATCHES)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }
                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchItemById(MatchItem item) {
        DocumentReference matchItemRef = db.collection(Constants.MATCHES).document(item.uid);
        Map<String, Object> data = new HashMap<>();
        data.put(Constants.LIKED, item.liked);
        matchItemRef.update(data);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(ListenerRegistration::remove);
    }
}
