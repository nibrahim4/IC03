package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* Nia Ibrahim
       Abhishek Tanwer
       Jahnvi Chitnis
     */
    public static final String TAG_IMAGE = "photoProf";
    public static final String ERROR_MESSAGE = "Please enter a name.";
    private  boolean isFirstNameErrorThrown = false;
    private boolean isLastNameErrorThrown = false;
    private boolean isGenderErrorThrown = false;
    RadioGroup rg_gender;
    RadioButton rb_Female;
    RadioButton rb_Male;
    ImageView iv_Gender;
    Button button_Save;
    EditText et_firstName;
    EditText et_lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_gender = findViewById(R.id.rg_gender);
        rb_Female = findViewById(R.id.rb_Male);
        rb_Male = findViewById(R.id.rb_Female);
        iv_Gender = findViewById(R.id.iv_propic);
        button_Save = findViewById(R.id.button_Save);
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);

        final String[] flag_image = {""};



        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_Female:
                        iv_Gender.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image[0] = "female";
                        break;
                    case R.id.rb_Male:
                        iv_Gender.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image[0] = "male";
                        break;
                    default:
                        break;
                }
            }
        });

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_firstName.getText().toString().equals("")){
                    et_firstName.setError(ERROR_MESSAGE);
                    isFirstNameErrorThrown = true;
                }else{
                    isFirstNameErrorThrown = false;
                }
                if(et_lastName.getText().toString().equals("")){
                    et_lastName.setError(ERROR_MESSAGE);
                    isLastNameErrorThrown = true;
                }else{
                    isLastNameErrorThrown = false;
                }
                if (flag_image[0].equals("") || flag_image[0] == ""){
                    isGenderErrorThrown = true;
                }else{
                    isGenderErrorThrown = false;
                }

                if (!isFirstNameErrorThrown && !isLastNameErrorThrown && !isGenderErrorThrown){
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);

                    User user = new User(flag_image[0],et_firstName.getText().toString(),et_lastName.getText().toString());
                    user.setGender(flag_image[0]);
                    user.setFirstName(et_firstName.getText().toString());
                    user.setLastName(et_lastName.getText().toString());

                    Bundle sentData = new Bundle();
                    sentData.putSerializable("user",user);

                    intent.putExtra(TAG_IMAGE, sentData);

                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Invalid Input!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
