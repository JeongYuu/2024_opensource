package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MenuActivity extends AppCompatActivity {
    private Button review_button;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView typeofname;
    private TextView typeofbread;
    private TextView typeofcheese;
    private TextView typeofsauce;
    private TextView typeofvege;
    private TextView typeofadd;
    private CardView menu_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        review_button = findViewById(R.id.review_button);

        typeofname = findViewById(R.id.typeofname);
        typeofbread = findViewById(R.id.typeofbread);
        typeofcheese = findViewById(R.id.typeofcheese);
        typeofsauce = findViewById(R.id.typeofsauce);
        typeofvege = findViewById(R.id.typeofvege);
        typeofadd = findViewById(R.id.typeofadd);

        menu_picture = findViewById(R.id.menu_picture);

        Intent intent = getIntent();

        try {
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String value1 = bundle.getString("value1");
                    String value2 = bundle.getString("value2");
                    db = FirebaseFirestore.getInstance();
                    DocumentReference docRef = db.collection(value1).document(value2);
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Menu menu = documentSnapshot.toObject(Menu.class);
                                typeofname.setText(menu.getName());
                                typeofbread.setText(menu.getBread());
                                typeofcheese.setText(menu.getCheese());
                                typeofsauce.setText(menu.getSauce());
                                typeofvege.setText(menu.getVege());
                                typeofadd.setText(menu.getAdd());
                            }
                        }
                    });

                    if (value1.equals("general_menu") && value2.equals("menu1")) {
                        menu_picture.setBackgroundResource(R.drawable.i_bmt);
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu2")) {
                        menu_picture.setBackgroundResource(R.drawable.eggmayo);
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu3")) {
                        menu_picture.setBackgroundResource(R.drawable.club);
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu4")) {
                        menu_picture.setBackgroundResource(R.drawable.stakeandcheese);
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu5")) {
                        menu_picture.setBackgroundResource(R.drawable.avocado);
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu6")) {
                        menu_picture.setBackgroundResource(R.drawable.k_bbq);
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu1")) {
                        menu_picture.setBackgroundResource(R.drawable.vege);
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu2")) {
                        menu_picture.setBackgroundResource(R.drawable.shrimp);
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu3")) {
                        menu_picture.setBackgroundResource(R.drawable.r_chicken);
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu4")) {
                        menu_picture.setBackgroundResource(R.drawable.r_bbq);
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu5")) {
                        menu_picture.setBackgroundResource(R.drawable.chicken_slice);
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu1")) {
                        menu_picture.setBackgroundResource(R.drawable.eggmayo);
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu2")) {
                        menu_picture.setBackgroundResource(R.drawable.club);
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu3")) {
                        menu_picture.setBackgroundResource(R.drawable.stakeandcheese);
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu4")) {
                        menu_picture.setBackgroundResource(R.drawable.blt);
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu5")) {
                        menu_picture.setBackgroundResource(R.drawable.shrimp);
                    }
                }
            }
        }catch(Exception e){
            Toast.makeText(this, "메뉴 정보를 불러오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();

        }

        try {
            review_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, ReviewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        } catch(Exception e){
            Toast.makeText(this, "리뷰 정보를 불러오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

    }



}
