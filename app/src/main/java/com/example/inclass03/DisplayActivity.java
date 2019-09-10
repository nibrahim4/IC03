package com.example.inclass03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.inclass03.MainActivity.TAG_IMAGE;

public class DisplayActivity extends AppCompatActivity {

    public static final String TAG_IMAGE = "photoProf";
    private static final int REQ_CODE = 5;
    private ImageView iv_profile;
    private Button button_Edit;
    private TextView tv_fullName;
    private TextView tv_gender;
    private Bundle extrasFromMain;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        iv_profile = findViewById(R.id.iv_profile);
        button_Edit = findViewById(R.id.button_Edit);
        tv_fullName = findViewById(R.id.tv_fullName);
        tv_gender = findViewById(R.id.tv_gender_display);

        extrasFromMain = getIntent().getExtras().getBundle(TAG_IMAGE);

        user = (User) extrasFromMain.getSerializable("user");

        if (user!=null){
            tv_fullName.setText("Name: " + user.getFirstName() + " " + user.getLastName());

            if (user.getGender().equals("male")) {
                iv_profile.setImageDrawable(getDrawable(R.drawable.male));
                tv_gender.setText("Male");
            }else if(user.getGender().equals("female")){
                iv_profile.setImageDrawable(getDrawable(R.drawable.female));
                tv_gender.setText("Female");
            }
        }

        button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(DisplayActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bundleEdit", user);
                toEdit.putExtra("toEdit", bundle);

                startActivityForResult(toEdit,REQ_CODE);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){
            if(resultCode==RESULT_OK){
                 extrasFromMain = data.getExtras().getBundle(TAG_IMAGE);

                 user = (User) extrasFromMain.getSerializable("usertoDisplay");
                if (user!=null){
                    tv_fullName.setText(user.getFirstName() + " " + user.getLastName());

                    if (user.getGender().equals("male")){
                        iv_profile.setImageDrawable(getDrawable(R.drawable.male));
                        tv_gender.setText(user.getGender());
                    }else{
                        iv_profile.setImageDrawable(getDrawable(R.drawable.female));
                        tv_gender.setText(user.getGender());
                    }

                }
            }
        }
    }
}
