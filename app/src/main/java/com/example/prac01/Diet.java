package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Diet extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);

        Button myPage = findViewById(R.id.mypage);

        Button generalbutton = findViewById(R.id.generalbutton);
        Button dietbutton = findViewById(R.id.dietbutton);
        Button premiumbutton = findViewById(R.id.premiumbutton);
        Button etcbutton = findViewById(R.id.etcbutton);

        LinearLayout diet_menu1 = findViewById(R.id.diet_menu1);
        LinearLayout diet_menu2 = findViewById(R.id.diet_menu2);
        LinearLayout diet_menu3 = findViewById(R.id.diet_menu3);
        LinearLayout diet_menu4 = findViewById(R.id.diet_menu4);
        LinearLayout diet_menu5 = findViewById(R.id.diet_menu5);

        try {
            myPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        check_Login_Diet();

                    } else {
                        change_Tap_Diet(User.class);
                    }
                }
            });
        }catch(Exception e){
            toast("리뷰 정보를 불러오는데에 실패했습니다.");
        }

        try{
            generalbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Diet(Main.class);
                }
            });
            dietbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Diet(Diet.class);
                }
            });
            premiumbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Diet(Premium.class);
                }
            });
            etcbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_Tap_Diet(Etc.class);
                }
            });
        }catch(Exception e){
            toast("탭 변경을 실패했습니다.");
        }

        try{
        diet_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_Menu_Diet("menu1");
            }
        });
        diet_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_Menu_Diet("menu2");
            }
        });
        diet_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_Menu_Diet("menu3");
            }
        });
        diet_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_Menu_Diet("menu4");
            }
        });
        diet_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_Menu_Diet("menu5");
            }
        });
        } catch(Exception e){
            toast("메뉴 정보를 불러오는데에 실패했습니다.");
        }

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            check_Login_Diet();
        }

    }

    public void check_Login_Diet(){
        Intent intent = new Intent(Diet.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        toast("로그인이 되어 있지 않습니다.");
    }

    public void change_Tap_Diet(Class<?> targetClass) {
        Intent intent = new Intent(Diet.this, targetClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void select_Menu_Diet(String menu_name){
        Intent intent = new Intent(Diet.this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("value1", "diet_menu");
        bundle.putString("value2", menu_name);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void toast(String text){
        Toast.makeText(Diet.this, text, Toast.LENGTH_SHORT).show();
    }

}
