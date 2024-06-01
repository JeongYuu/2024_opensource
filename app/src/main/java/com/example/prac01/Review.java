package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Review extends AppCompatActivity {

    private TextInputEditText review_input;
    private TextView review_menu_name;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        review_input = findViewById(R.id.review_input);
        Button review_register = findViewById(R.id.review_register);
        review_menu_name = findViewById(R.id.review_menu_name);
        recyclerview = findViewById(R.id.recyclerview);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        if (intent != null) {
                review_menu_name.setText(intent.getStringExtra("menu_name"));
        }
        review_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReview();
            }

        });

        db.collection("Review")
                .document(review_menu_name.getText().toString())
                .collection("review")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        setReview(value);
                    }
                });
    }

    public void registerReview(){
        if(!review_input.getText().toString().equals("")) {
            ReviewInfo reviewInfo = new ReviewInfo(mAuth.getCurrentUser().getUid(), review_menu_name.getText().toString(), review_input.getText().toString());
            db.collection("Review").document(review_menu_name.getText().toString()).collection("review").document().set(reviewInfo);
            db.collection("user").document(mAuth.getCurrentUser().getUid()).collection("review").document().set(reviewInfo);
            review_input.setText("");
        }
    }

    public void setReview(QuerySnapshot value){
        ArrayList<ReviewInfo> reviewInfoList = new ArrayList<>();

        for(QueryDocumentSnapshot documentSnapshot : value) {
            ReviewInfo reviewInfo = documentSnapshot.toObject(ReviewInfo.class);
            reviewInfoList.add(reviewInfo);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Review.this);
        recyclerview.setLayoutManager(layoutManager);
        reviewAdapter = new ReviewAdapter(reviewInfoList, Review.this);
        recyclerview.setAdapter(reviewAdapter);
    }

}
