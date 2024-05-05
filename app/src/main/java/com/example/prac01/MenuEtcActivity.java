package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuEtcActivity extends AppCompatActivity {
    private Button review_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_etc);

        review_button = findViewById(R.id.review_button);
        review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEtcActivity.this, ReviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


}
