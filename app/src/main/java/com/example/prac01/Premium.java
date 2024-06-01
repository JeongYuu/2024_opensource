package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Premium extends AppCompatActivity {
    private Button myPage;
    private Button generalbutton;
    private Button dietbutton;
    private Button premiumbutton;
    private Button etcbutton;

    private LinearLayout premium_menu1;
    private LinearLayout premium_menu2;
    private LinearLayout premium_menu3;
    private LinearLayout premium_menu4;
    private LinearLayout premium_menu5;
    private LinearLayout premium_menu6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium);

        myPage = findViewById(R.id.mypage);

        generalbutton = findViewById(R.id.generalbutton);
        dietbutton = findViewById(R.id.dietbutton);
        premiumbutton = findViewById(R.id.premiumbutton);
        etcbutton = findViewById(R.id.etcbutton);

        premium_menu1 = findViewById(R.id.premium_menu1);
        premium_menu2 = findViewById(R.id.premium_menu2);
        premium_menu3 = findViewById(R.id.premium_menu3);
        premium_menu4 = findViewById(R.id.premium_menu4);
        premium_menu5 = findViewById(R.id.premium_menu5);


        try {
            myPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        check_Login_Premium();

                    } else {
                        change_Tap_Premium(User.class);
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
                    change_Tap_Premium(Main.class);
                }
            });
            dietbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Premium(Diet.class);
                }
            });
            premiumbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Premium(Premium.class);
                }
            });
            etcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Premium(Etc.class);
                }
            });
        }catch(Exception e){
            toast("탭 변경을 실패했습니다.");
        }

        try {
            premium_menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Premium("menu1");
                }
            });
            premium_menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Premium("menu2");
                }
            });
            premium_menu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Premium("menu3");
                }
            });
            premium_menu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Premium("menu4");
                }
            });
            premium_menu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select_Menu_Premium("menu5");
                }
            });
        }catch(Exception e){
            toast("메뉴 정보를 불러오는데에 실패했습니다.");
        }


        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            check_Login_Premium();
        }

    }
    public void check_Login_Premium(){
        Intent intent = new Intent(Premium.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        toast("로그인이 되어 있지 않습니다.");
    }

    public void change_Tap_Premium(Class<?> targetClass) {
        Intent intent = new Intent(Premium.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void select_Menu_Premium(String menu_name){
        Intent intent = new Intent(Premium.this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("value1", "premium_menu");
        bundle.putString("value2", menu_name);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(Premium.this, text, Toast.LENGTH_SHORT).show();
    }
}
