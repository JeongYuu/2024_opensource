package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class EtcActivity extends AppCompatActivity {
    private Button myPage;
    private Button generalbutton;
    private Button dietbutton;
    private Button premiumbutton;
    private Button etcbutton;

    private LinearLayout etc_menu1;
    private LinearLayout etc_menu2;
    private LinearLayout etc_menu3;
    private LinearLayout etc_menu4;
    private LinearLayout etc_menu5;
    private LinearLayout etc_menu6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etc);

        myPage = findViewById(R.id.mypage); //화면이랑 id랑 연결해주는거

        generalbutton = findViewById(R.id.generalbutton);
        dietbutton = findViewById(R.id.dietbutton);
        premiumbutton = findViewById(R.id.premiumbutton);
        etcbutton = findViewById(R.id.etcbutton);

        etc_menu1 = findViewById(R.id.etc_menu1);
        etc_menu2 = findViewById(R.id.etc_menu2);
        etc_menu3 = findViewById(R.id.etc_menu3);
        etc_menu4 = findViewById(R.id.etc_menu4);
        etc_menu5 = findViewById(R.id.etc_menu5);
        etc_menu6 = findViewById(R.id.etc_menu6);

        try {
            myPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        Intent intent = new Intent(EtcActivity.this, SignUpActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Toast.makeText(EtcActivity.this, "로그인이 되어 있지 않습니다.", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(EtcActivity.this, UserPageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            });
        } catch(Exception e){
            Toast.makeText(this, "리뷰 정보를 가져오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

        try {
            generalbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            dietbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, DietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            premiumbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, PremiumActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, EtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            Toast.makeText(this, "탭 변경을 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

        try {
            etc_menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MenuEtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etc_menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MenuEtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etc_menu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MenuEtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etc_menu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MenuEtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etc_menu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MenuEtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etc_menu6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EtcActivity.this, MenuEtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            Toast.makeText(this, "메뉴 정보를 불러오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }



        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(EtcActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

}
