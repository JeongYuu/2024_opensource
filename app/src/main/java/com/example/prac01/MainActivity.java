package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("로그인")) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    FirebaseAuth.getInstance().signOut();
                }
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {

            // 회원가입 or 로그인
            /*DocumentReference userRef = db.collection("users").document(user.getUid());

            Map<String, Object> userData = new HashMap<>();
            userData.put("email", user.getEmail());
            userData.put("userId", user.getUid());
            userRef.set(userData);*/


        }
    }

    public void onButton1Clicked(View v){
        Toast.makeText(this, "확인1 버튼이 눌렸음", Toast.LENGTH_LONG).show();
    }



}