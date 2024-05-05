package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.StringTokenizer;

public class UserPageActivity extends AppCompatActivity {

    private Button logout;
    private TextView nickname;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        logout = findViewById(R.id.logout); //버튼 연결
        nickname = findViewById(R.id.nickname);
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() { //버튼 누르면 로그아웃
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut(); //로그아웃
                Intent intent = new Intent(UserPageActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

       // nickname.setText(mAuth.getCurrentUser().getUid().substring(0,10));
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("user").document(mAuth.getCurrentUser().getUid()).collection("userInfo").document("Info");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    User user = documentSnapshot.toObject(User.class);
                    String email = user.getEmail();
                    StringTokenizer st = new StringTokenizer(email, "@");
                    nickname.setText(st.nextToken());
                }
            }
        });

    }
}
