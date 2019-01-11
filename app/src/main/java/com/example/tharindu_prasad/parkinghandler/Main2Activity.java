package com.example.tharindu_prasad.parkinghandler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
        checkpassword();
        checkname();
    }

    public void OnLogin(View view) {

        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);




    }
    private void checkpassword() {
        // TODO Auto-generated method stub

        PasswordEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Password(PasswordEt); // pass your EditText Obj here.
            }

            public void Is_Valid_Password(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Password");
                    //valid_password = null;
                } else if (isPasswordValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid password");
                   // valid_password = null;
                } else {
                  //  valid_password = edt.getText().toString();
                }
            }
            boolean isPasswordValid(String password) {
                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,12}";
                return password.matches(pattern);
            }



        });
    }
    private void checkname(){
        // TODO Auto-generated method stub

        UsernameEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                UsernameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(UsernameEt.getText().length()<4){
                            UsernameEt.setError("min 4 characters");
                        }
                        else if(UsernameEt.getText().length()>20){
                            UsernameEt.setError("max 20 characters");
                        }
                    }
                });
            }
        });

    }
}
