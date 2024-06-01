package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Etc extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etc);

        Button myPage = findViewById(R.id.mypage);

        Button generalbutton = findViewById(R.id.generalbutton);
        Button dietbutton = findViewById(R.id.dietbutton);
        Button premiumbutton = findViewById(R.id.premiumbutton);
        Button etcbutton = findViewById(R.id.etcbutton);

        LinearLayout etc_menu1 = findViewById(R.id.etc_menu1);
        LinearLayout etc_menu2 = findViewById(R.id.etc_menu2);
        LinearLayout etc_menu3 = findViewById(R.id.etc_menu3);
        LinearLayout etc_menu4 = findViewById(R.id.etc_menu4);
        LinearLayout etc_menu5 = findViewById(R.id.etc_menu5);
        LinearLayout etc_menu6 = findViewById(R.id.etc_menu6);

        try {
            myPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        check_Login_Etc();

                    } else {
                        change_Tap_Etc(User.class);
                    }
                }
            });
        } catch(Exception e){
            toast("리뷰 정보를 불러오는데에 실패했습니다.");
        }

        try {
            generalbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Etc(Main.class);
                }
            });
            dietbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Etc(Diet.class);
                }
            });
            premiumbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Etc(Premium.class);
                }
            });
            etcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Etc(Etc.class);
                }
            });
        }catch(Exception e){
            toast("탭 변경을 실패했습니다.");
        }

        try {
            etc_menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Etc("menu1");
                }
            });
            etc_menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Etc("menu2");
                }
            });
            etc_menu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Etc("menu3");
                }
            });
            etc_menu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Etc("menu4");
                }
            });
            etc_menu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Etc("menu5");
                }
            });
            etc_menu6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Etc("menu6");
                }
            });
        }catch(Exception e){
            toast("메뉴 정보를 불러오는데에 실패했습니다.");
        }



        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            check_Login_Etc();
        }

    }
    public void check_Login_Etc(){
        Intent intent = new Intent(Etc.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        toast("로그인이 되어 있지 않습니다.");
    }

    public void change_Tap_Etc(Class<?> targetClass) {
        Intent intent = new Intent(Etc.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void select_Menu_Etc(String menu_name){
        Intent intent = new Intent(Etc.this, MenuEtc.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("value1", "etc_menu");
        bundle.putString("value2", menu_name);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(Etc.this, text, Toast.LENGTH_SHORT).show();
    }

}
