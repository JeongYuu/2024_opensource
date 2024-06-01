package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoLoginButton).setOnClickListener(onClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    View.OnClickListener onClickListener = view -> {
        if (view.getId() == R.id.signUpButton) {
            signUp();
        } else if (view.getId() == R.id.gotoLoginButton) {
            change_Tap_Signup(Login.class);
        }
    };

    private void signUp() {
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordCheckEditText)).getText().toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {
            if(password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                mAuth.getCurrentUser();
                                UserInfo userInfo = new UserInfo(email, password);

                                db.collection("user").document(mAuth.getCurrentUser().getUid()).collection("userInfo").document("Info").set(userInfo);
                                toast("회원가입에 성공하였습니다.");
                                Intent intent = new Intent(SignUp.this, Main.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else if (task.getException() != null) {
                                String exceptionMessage = task.getException().toString();
                                if (exceptionMessage.contains("FirebaseAuthWeakPasswordException")) {
                                    toast("비밀번호는 6자리 이상이어야 합니다.");
                                } else if (exceptionMessage.contains("FirebaseAuthUserCollisionException")) {
                                    toast("이미 가입한 계정이 있습니다.");
                                } else {
                                    toast("회원가입에 실패하였습니다.");
                                }
                            }
                        });
            } else {
                toast( "비밀번호가 일치하지 않습니다.");
            }
        } else {
            toast("이메일 또는 비밀번호를 입력해 주세요.");
        }

    }
    public void change_Tap_Signup(Class<?> targetClass) {
        Intent intent = new Intent(SignUp.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(SignUp.this, text, Toast.LENGTH_SHORT).show();
    }

}