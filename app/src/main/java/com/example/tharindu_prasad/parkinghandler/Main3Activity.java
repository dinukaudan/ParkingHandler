package com.example.tharindu_prasad.parkinghandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
   public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    button=(Button)findViewById(R.id.button);

    }

}
