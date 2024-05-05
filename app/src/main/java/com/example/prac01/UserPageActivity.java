package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class UserPageActivity extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        logout = findViewById(R.id.logout); //버튼 연결

        logout.setOnClickListener(new View.OnClickListener() { //버튼 누르면 로그아웃
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut(); //로그아웃
                Intent intent = new Intent(UserPageActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
