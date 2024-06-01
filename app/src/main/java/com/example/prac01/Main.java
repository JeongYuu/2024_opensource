package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity {
    private Button myPage;
    private Button generalbutton;
    private Button dietbutton;
    private Button premiumbutton;
    private Button etcbutton;

    private LinearLayout general_menu1;
    private LinearLayout general_menu2;
    private LinearLayout general_menu3;
    private LinearLayout general_menu4;
    private LinearLayout general_menu5;
    private LinearLayout general_menu6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPage = findViewById(R.id.mypage);

        generalbutton = findViewById(R.id.generalbutton);
        dietbutton = findViewById(R.id.dietbutton);
        premiumbutton = findViewById(R.id.premiumbutton);
        etcbutton = findViewById(R.id.etcbutton);

        general_menu1 = findViewById(R.id.general_menu1);
        general_menu2 = findViewById(R.id.general_menu2);
        general_menu3 = findViewById(R.id.general_menu3);
        general_menu4 = findViewById(R.id.general_menu4);
        general_menu5 = findViewById(R.id.general_menu5);
        general_menu6 = findViewById(R.id.general_menu6);


        try {
            myPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        check_Login_Main(Login.class);
                    } else {
                        change_Tap_Main(User.class);
                    }
                }
            });
        }catch(Exception e){
            toast("리뷰 정보를 불러오는데에 실패했습니다.");
        }

        try {
            generalbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Main(Main.class);
                }
            });


            dietbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Main(Diet.class);
                }
            });

            premiumbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Main(Premium.class);
                }
            });
            etcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Main(Etc.class);
                }
            });
        }catch(Exception e){
            toast("탭 변경을 실패했습니다.");
        }

        try {
            general_menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Main("menu1");
                }
            });
            general_menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Main("menu2");
                }
            });
            general_menu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Main("menu3");
                }
            });
            general_menu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Main("menu4");
                }
            });
            general_menu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Main("menu5");
                }
            });
            general_menu6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Main("menu6");
                }
            });
        } catch (Exception e){
            toast("메뉴 정보를 불러오는데에 실패했습니다.");
        }

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            check_Login_Main(Login.class);
        }
    }

    public void check_Login_Main(Class<?> targetClass){
        Intent intent = new Intent(Main.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        toast("로그인이 되어 있지 않습니다.");
    }

    public void change_Tap_Main(Class<?> targetClass) {
        Intent intent = new Intent(Main.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void select_Menu_Main(String menu_name){
        Intent intent = new Intent(Main.this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("value1", "general_menu");
        bundle.putString("value2", menu_name);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(Main.this, text, Toast.LENGTH_SHORT).show();
    }

}