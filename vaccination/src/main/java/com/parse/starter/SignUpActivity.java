package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {



    String phoneno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);




    }


    public void signUp (View view){

        EditText usernameEditText= (EditText) findViewById(R.id.usernameEditText);

        EditText passwordEditText= (EditText) findViewById(R.id.passwordEditText);
        if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){

            Toast.makeText( this,"A username and mobile number are required",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ParseObject parentuser = new ParseObject("parentuser");

            parentuser.put("name",usernameEditText.getText().toString());
          phoneno = passwordEditText.getText().toString();

            //parentuser.setPassword(phoneno);
            parentuser.put("PhoneNumber",passwordEditText.getText().toString());


            parentuser.saveInBackground(new SaveCallback( ) {
                @Override
                public void done(ParseException e) {
                    if(e==null)
                    {
                     Toast.makeText(SignUpActivity.this,"sign up succesfull",Toast.LENGTH_SHORT);
                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"unsucessfull",Toast.LENGTH_SHORT);

                    }
                }
            });
            Intent launchNextActivity;
            launchNextActivity = new Intent(SignUpActivity.this, regionpicker.class);
            launchNextActivity.putExtra("phoneno",phoneno);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);

        }
    }




}
