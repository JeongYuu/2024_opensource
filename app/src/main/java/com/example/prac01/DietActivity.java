package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DietActivity extends AppCompatActivity {
    private Button myPage;
    private Button generalbutton;
    private Button dietbutton;
    private Button premiumbutton;
    private Button etcbutton;

    private LinearLayout diet_menu1;
    private LinearLayout diet_menu2;
    private LinearLayout diet_menu3;
    private LinearLayout diet_menu4;
    private LinearLayout diet_menu5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);

        myPage = findViewById(R.id.mypage); //화면이랑 id랑 연결해주는거

        generalbutton = findViewById(R.id.generalbutton);
        dietbutton = findViewById(R.id.dietbutton);
        premiumbutton = findViewById(R.id.premiumbutton);
        etcbutton = findViewById(R.id.etcbutton);

        diet_menu1 = findViewById(R.id.diet_menu1);
        diet_menu2 = findViewById(R.id.diet_menu2);
        diet_menu3 = findViewById(R.id.diet_menu3);
        diet_menu4 = findViewById(R.id.diet_menu4);
        diet_menu5 = findViewById(R.id.diet_menu5);

        try {
            myPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        Intent intent = new Intent(DietActivity.this, SignUpActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Toast.makeText(DietActivity.this, "로그인이 되어 있지 않습니다.", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(DietActivity.this, UserPageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            });
        }catch(Exception e){
            Toast.makeText(this, "리뷰 정보를 가져오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

        try{
            generalbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            dietbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietActivity.this, DietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            premiumbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietActivity.this, PremiumActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            etcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DietActivity.this, EtcActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            Toast.makeText(this, "탭 변경을 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

        try{
        diet_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        diet_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        diet_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        diet_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        diet_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        } catch(Exception e){
            Toast.makeText(this, "메뉴 정보를 불러오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }



        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(DietActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

}
