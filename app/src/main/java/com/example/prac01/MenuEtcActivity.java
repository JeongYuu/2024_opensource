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

public class MenuEtcActivity extends AppCompatActivity {
    private Button review_button;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CardView menuetc_picture;
    private TextView typeofetcname;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_etc);

        review_button = findViewById(R.id.review_button);

        menuetc_picture = findViewById(R.id.menuetc_picture);

        typeofetcname = findViewById(R.id.typeofetcname);

        description = findViewById(R.id.description);

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
                                MenuEtc menuetc = documentSnapshot.toObject(MenuEtc.class);
                                typeofetcname.setText(menuetc.getName());
                                description.setText(menuetc.getDescription());
                            }
                        }
                    });

                    if (value1.equals("etc_menu") && value2.equals("menu1")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c1);
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu2")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c2);
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu3")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c3);
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu4")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c4);
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu5")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c5);
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu6")) {
                        menuetc_picture.setBackgroundResource(R.drawable.hash);
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
                    Intent intent = new Intent(MenuEtcActivity.this, ReviewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            Toast.makeText(this, "리뷰 정보를 가져오는데에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

    }


}
