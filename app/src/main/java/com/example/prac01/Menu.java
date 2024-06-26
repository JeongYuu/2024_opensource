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

public class Menu extends AppCompatActivity {
    private Button review_button;
    private TextView typeofname;
    private TextView typeofbread;
    private TextView typeofcheese;
    private TextView typeofsauce;
    private TextView typeofvege;
    private TextView typeofadd;
    private CardView menu_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        String menu_name = "";

        try {
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String value1 = bundle.getString("value1");
                    String value2 = bundle.getString("value2");
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    DocumentReference docRef = db.collection(value1).document(value2);
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                set_Menu(documentSnapshot);
                            }
                        }
                    });

                    if (value1.equals("general_menu") && value2.equals("menu1")) {
                        menu_picture.setBackgroundResource(R.drawable.i_bmt);
                        menu_name = "이탈리안 BMT";
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu2")) {
                        menu_picture.setBackgroundResource(R.drawable.eggmayo);
                        menu_name = "에그마요";
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu3")) {
                        menu_picture.setBackgroundResource(R.drawable.club);
                        menu_name = "서브웨이 클럽";
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu4")) {
                        menu_picture.setBackgroundResource(R.drawable.stakeandcheese);
                        menu_name = "스테이크 앤 치즈";
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu5")) {
                        menu_picture.setBackgroundResource(R.drawable.avocado);
                        menu_name = "치킨 베이컨 아보카도";
                    }
                    else if (value1.equals("general_menu") && value2.equals("menu6")) {
                        menu_picture.setBackgroundResource(R.drawable.k_bbq);
                        menu_name = "k-바베큐";
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu1")) {
                        menu_picture.setBackgroundResource(R.drawable.vege);
                        menu_name = "베지";
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu2")) {
                        menu_picture.setBackgroundResource(R.drawable.shrimp);
                        menu_name = "쉬림프";
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu3")) {
                        menu_picture.setBackgroundResource(R.drawable.r_chicken);
                        menu_name = "로스트 치킨";
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu4")) {
                        menu_picture.setBackgroundResource(R.drawable.r_bbq);
                        menu_name = "로티세리 바베큐";
                    }
                    else if (value1.equals("diet_menu") && value2.equals("menu5")) {
                        menu_picture.setBackgroundResource(R.drawable.chicken_slice);
                        menu_name = "치킨 슬라이스";
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu1")) {
                        menu_picture.setBackgroundResource(R.drawable.eggmayo);
                        menu_name = "에그마요(고급)";
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu2")) {
                        menu_picture.setBackgroundResource(R.drawable.club);
                        menu_name = "서브웨이 클럽(고급)";
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu3")) {
                        menu_picture.setBackgroundResource(R.drawable.stakeandcheese);
                        menu_name = "스테이크 앤 치즈(고급)";
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu4")) {
                        menu_picture.setBackgroundResource(R.drawable.blt);
                        menu_name = "비엘티(고급)";
                    }
                    else if (value1.equals("premium_menu") && value2.equals("menu5")) {
                        menu_picture.setBackgroundResource(R.drawable.shrimp);
                        menu_name = "쉬림프(고급)";
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
                        check_Login_Menu();
                    } else {
                        Intent intent = new Intent(Menu.this, Review.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("menu_name", finalMenu_name.toString());
                        startActivity(intent);
                    }
                }
            });
        } catch(Exception e){
            toast("리뷰 정보를 불러오는데에 실패했습니다.");
        }

    }

    public void check_Login_Menu(){
        Intent intent = new Intent(Menu.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        toast("로그인이 되어 있지 않습니다.");
    }

    public void set_Menu(DocumentSnapshot documentSnapshot) {
        MenuInfo menuInfo = documentSnapshot.toObject(MenuInfo.class);
        typeofname.setText(menuInfo.getName());
        typeofbread.setText(menuInfo.getBread());
        typeofcheese.setText(menuInfo.getCheese());
        typeofsauce.setText(menuInfo.getSauce());
        typeofvege.setText(menuInfo.getVege());
        typeofadd.setText(menuInfo.getAdd());
    }

    public void toast(String text){
        Toast.makeText(Menu.this, text, Toast.LENGTH_SHORT).show();
    }
}
