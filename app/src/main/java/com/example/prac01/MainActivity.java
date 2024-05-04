package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button myPage;
    private LinearLayout menu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPage = findViewById(R.id.mypage); //화면이랑 연결해주는거
        menu1 = findViewById(R.id.menu1);

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, UserpageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(button.getText().equals("로그인")) {
//                    Intent intent = new Intent(MainActivity.this, Login.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                } else {
//                    FirebaseAuth.getInstance().signOut();
//                    button.setText("로그인");
//                }
//            }
//        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
//        else {
//            button.setText("로그아웃");
            // 회원가입 or 로그인
            /*DocumentReference userRef = db.collection("users").document(user.getUid());

            Map<String, Object> userData = new HashMap<>();
            userData.put("email", user.getEmail());
            userData.put("userId", user.getUid());
            userRef.set(userData);*/

        //}
    }

    public void onButton1Clicked(View v){
        Toast.makeText(this, "확인1 버튼이 눌렸음", Toast.LENGTH_LONG).show();
    }



}