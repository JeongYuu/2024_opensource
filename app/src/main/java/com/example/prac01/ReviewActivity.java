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

public class ReviewActivity  extends AppCompatActivity {

    private TextInputEditText review_input;
    private Button review_register;
    private TextView review_menu_name;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        review_input = findViewById(R.id.review_input);
        review_register = findViewById(R.id.review_register);
        review_menu_name = findViewById(R.id.review_menu_name);
        recyclerview = findViewById(R.id.recyclerview);

        Intent intent = getIntent();

        if (intent != null) {
                review_menu_name.setText(intent.getStringExtra("menu_name"));
        }
        review_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!review_input.getText().toString().equals("")) {
                    mAuth = FirebaseAuth.getInstance();
                    db = FirebaseFirestore.getInstance();
                    Review review = new Review(mAuth.getCurrentUser().getUid(), review_menu_name.getText().toString(), review_input.getText().toString());
                    db = FirebaseFirestore.getInstance();
                    db.collection("Review").document(review_menu_name.getText().toString()).collection("review").document().set(review);
                    db.collection("user").document(mAuth.getCurrentUser().getUid()).collection("review").document().set(review);
                    review_input.setText("");
                }
            }

        });

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        db.collection("Review")
                .document(review_menu_name.getText().toString())
                .collection("review")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        ArrayList<Review> reviewList = new ArrayList<>();

                        for(QueryDocumentSnapshot documentSnapshot : value) {
                            Review review = documentSnapshot.toObject(Review.class);
                            reviewList.add(review);
                        }

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReviewActivity.this);
                        recyclerview.setLayoutManager(layoutManager);
                        reviewAdapter = new ReviewAdapter(reviewList, ReviewActivity.this);
                        recyclerview.setAdapter(reviewAdapter);
                    }
                });
    }

}
