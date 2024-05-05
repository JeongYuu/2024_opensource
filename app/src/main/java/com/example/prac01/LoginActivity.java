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


public class LoginActivity extends AppCompatActivity{
    //private static final int RC_SIGN_IN = 10;

    // FirebaseAuth의 인스턴스를 선언
    private FirebaseAuth mAuth;

    private Button signupbutton;
    /*    private GoogleSignInClient mGoogleSignInClient;

        private final ActivityResultLauncher<Intent> googleSignInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                            if (signInResult != null && signInResult.isSuccess()) {
                                GoogleSignInAccount account = signInResult.getSignInAccount();
                                if (account != null) {
                                    firebaseAuthWithGoogle(account);
                                }
                            }
                        }
                    }
                });
    */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        signupbutton = findViewById(R.id.signupbutton);

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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

        if(email.length() > 0 && password.length() > 0 ) {
            //final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout);
            //loaderLayout.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        //loaderLayout.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser();
                            startToast("로그인에 성공하였습니다.");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            startToast("아이디 또는 비밀번호가 일치하지 않습니다.");
                        }
                    });
        } else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }

    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}