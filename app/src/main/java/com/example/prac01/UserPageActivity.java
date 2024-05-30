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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class UserPageActivity extends AppCompatActivity {

    private Button logout;
    private TextView nickname;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private MyReviewAdapter myReviewAdapter;
    private RecyclerView myrecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        logout = findViewById(R.id.logout);
        nickname = findViewById(R.id.nickname);
        mAuth = FirebaseAuth.getInstance();
        myrecyclerview = findViewById(R.id.myrecyclerview);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserPageActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("user").document(mAuth.getCurrentUser().getUid()).collection("userInfo").document("Info");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    User user = documentSnapshot.toObject(User.class);
                    String email = user.getEmail();
                    StringTokenizer st = new StringTokenizer(email, "@");
                    nickname.setText(st.nextToken()+" 님");
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        db.collection("user")
                .document(mAuth.getCurrentUser().getUid())
                .collection("review")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        ArrayList<Review> reviewList = new ArrayList<>();

                        for(QueryDocumentSnapshot documentSnapshot : value) {
                            Review review = documentSnapshot.toObject(Review.class);
                            reviewList.add(review);
                        }

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserPageActivity.this);
                        myrecyclerview.setLayoutManager(layoutManager);
                        myReviewAdapter = new MyReviewAdapter(reviewList, UserPageActivity.this);
                        myrecyclerview.setAdapter(myReviewAdapter);
                    }
                });

    }
}
