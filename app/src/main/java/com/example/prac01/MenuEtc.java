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

public class MenuEtc extends AppCompatActivity {
    private Button review_button;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CardView menuetc_picture;
    private TextView typeofetcname;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_etc);

        review_button = findViewById(R.id.review_button);

        menuetc_picture = findViewById(R.id.menuetc_picture);

        typeofetcname = findViewById(R.id.typeofetcname);

        description = findViewById(R.id.description);

        Intent intent = getIntent();

        String menu_name = "";

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
                                set_menu(documentSnapshot);
                            }
                        }
                    });

                    if (value1.equals("etc_menu") && value2.equals("menu1")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c1);
                        menu_name = "초코칩";
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu2")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c2);
                        menu_name = "더블 초코칩";
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu3")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c3);
                        menu_name = "오트밀 레이즌";
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu4")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c4);
                        menu_name = "라즈베리 치즈케익";
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu5")) {
                        menuetc_picture.setBackgroundResource(R.drawable.c5);
                        menu_name = "화이트 초코 마카다미아";
                    }
                    else if (value1.equals("etc_menu") && value2.equals("menu6")) {
                        menuetc_picture.setBackgroundResource(R.drawable.hash);
                        menu_name = "해쉬브라운";
                    }
                }
            }
        }catch(Exception e){
            toast("메뉴 정보를 불러오는데에 실패했습니다.");
        }

        try {
            String finalMenu_name = menu_name;
            review_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        check_Login_MenuEtc();
                    } else {
                        Intent intent = new Intent(MenuEtc.this, Review.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("menu_name", finalMenu_name.toString());
                        startActivity(intent);

                    }
                }
            });
        }catch(Exception e){
            toast("리뷰 정보를 가져오는데에 실패했습니다.");
        }

    }
    public void toast(String text){
        Toast.makeText(MenuEtc.this, text, Toast.LENGTH_SHORT).show();
    }

    public void check_Login_MenuEtc(){
        Intent intent = new Intent(MenuEtc.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        toast("로그인이 되어 있지 않습니다.");
    }

    public void set_menu(DocumentSnapshot documentSnapshot) {
        MenuEtcInfo menuetcInfo = documentSnapshot.toObject(MenuEtcInfo.class);
        typeofetcname.setText(menuetcInfo.getName());
        description.setText(menuetcInfo.getDescription());
    }

}
