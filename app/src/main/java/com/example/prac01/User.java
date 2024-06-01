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

public class User extends AppCompatActivity {

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
        myrecyclerview = findViewById(R.id.myrecyclerview);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogout();
            }
        });


        DocumentReference docRef = db.collection("user").document(mAuth.getCurrentUser().getUid()).collection("userInfo").document("Info");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    UserInfo userInfo = documentSnapshot.toObject(UserInfo.class);
                    String email = userInfo.getEmail();
                    StringTokenizer st = new StringTokenizer(email, "@");
                    nickname.setText(st.nextToken()+" 님");
                }
            }
        });

        db.collection("user")
                .document(mAuth.getCurrentUser().getUid())
                .collection("review")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        setMyReview(value);
                    }
                });

    }

    public void setLogout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(User.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void setMyReview(QuerySnapshot value){
        ArrayList<ReviewInfo> reviewInfoList = new ArrayList<>();

        for(QueryDocumentSnapshot documentSnapshot : value) {
            ReviewInfo reviewInfo = documentSnapshot.toObject(ReviewInfo.class);
            reviewInfoList.add(reviewInfo);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(User.this);
        myrecyclerview.setLayoutManager(layoutManager);
        myReviewAdapter = new MyReviewAdapter(reviewInfoList, User.this);
        myrecyclerview.setAdapter(myReviewAdapter);
    }
}
