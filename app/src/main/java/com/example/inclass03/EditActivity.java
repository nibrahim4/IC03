package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.inclass03.MainActivity.ERROR_MESSAGE;
import static com.example.inclass03.MainActivity.TAG_IMAGE;

public class EditActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    ImageView iv_propic;
    RadioButton rb_male;
    RadioButton rb_female;
    EditText et_firstName_edit;
    EditText et_lastName_edit;
    Button button_Save;
    private User user;
    private Bundle extrasFromDisplay;
    private boolean isErrorThrown =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        radioGroup = findViewById(R.id.rg_gender_edit);
        iv_propic = findViewById(R.id.iv_profile_edit);
        rb_female = findViewById(R.id.rb_Female_edit);
        rb_male = findViewById(R.id.rb_Male_edit);
        et_firstName_edit = findViewById(R.id.et_firstName_edit);
        et_lastName_edit = findViewById(R.id.et_lastName_edit);
        button_Save = findViewById(R.id.button_Save_edit);

        extrasFromDisplay = getIntent().getExtras().getBundle("toEdit");
        user = (User) extrasFromDisplay.getSerializable("bundleEdit");

        isErrorThrown = false;
      //SETTING THE FLAG ON CLICK...
        final String[] flag_image = {""};

       //SETTING RECEIVED DATA...
        if(user!=null){

            et_firstName_edit.setText(user.getFirstName());
            et_lastName_edit.setText(user.getLastName());

            if (user.getGender().equals("male")){
                rb_male.setChecked(true);
                user.setGender("male");
                flag_image[0] = "male";
                iv_propic.setImageDrawable(getDrawable(R.drawable.male));
            }else if(user.getGender().equals("female")){
                rb_female.setChecked(true);
                user.setGender("female");
                flag_image[0] = "female";
                iv_propic.setImageDrawable(getDrawable(R.drawable.female));
            }
        }



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_Female_edit:
                        iv_propic.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image[0] = "female";
                        user.setGender(flag_image[0]);
                        break;
                    case R.id.rb_Male_edit:
                        iv_propic.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image[0] = "male";
                        user.setGender(flag_image[0]);
                        break;
                    default:
                        break;
                }
            }
        });

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_firstName_edit.getText().toString().equals("")){
                    et_firstName_edit.setError(ERROR_MESSAGE);
                    isErrorThrown = true;
                }else{
                    isErrorThrown = false;
                }
                if(et_lastName_edit.getText().toString().equals("")){
                    et_lastName_edit.setError(ERROR_MESSAGE);
                    isErrorThrown = true;
                }else{
                    isErrorThrown = false;
                }

                if(!isErrorThrown){

                    User user = new User(flag_image[0],et_firstName_edit.getText().toString(),et_lastName_edit.getText().toString());
                    user.setFirstName(et_firstName_edit.getText().toString());
                    user.setLastName(et_lastName_edit.getText().toString());
                    user.setGender(flag_image[0]);
                    Bundle sentData = new Bundle();
                    sentData.putSerializable("usertoDisplay",user);
                    Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                    intent.putExtra(TAG_IMAGE, sentData);

//                startActivity(intent);

//                SETTING RESULTS...
                    setResult(EditActivity.RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(EditActivity.this, "Invalid Input!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
