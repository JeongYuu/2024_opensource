package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button signupbutton = findViewById(R.id.signupbutton);

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_Tap_Login(SignUp.class);
            }
        });
    }

    View.OnClickListener onClickListener = view -> {
        if (view.getId() == R.id.loginButton) {
            login();
        }
    };

    private void login() {
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if(email.length() > 0 && password.length() > 0 ) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser();
                            toast("로그인에 성공하였습니다.");
                            Intent intent = new Intent(Login.this, Main.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            toast("아이디 또는 비밀번호가 일치하지 않습니다.");
                        }
                    });
        } else {
            toast("아이디 또는 비밀번호를 입력해 주세요.");
        }

    }

    public void change_Tap_Login(Class<?> targetClass) {
        Intent intent = new Intent(Login.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
    }

}