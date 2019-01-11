package com.example.tharindu_prasad.parkinghandler;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class b_slot extends AppCompatActivity {
    private    Button button;
    private EditText Id,Uid;
    AlertDialog.Builder builder;
    private String url_base="https://benighted-places.000webhostapp.com/slot_book.php";
    private String url_base1="https://benighted-places.000webhostapp.com/confirmres.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_slot);
        button=(Button)findViewById(R.id.bn);
        Id=(EditText)findViewById(R.id.id);
        Uid=(EditText)findViewById(R.id.uid);
        builder=new AlertDialog.Builder(b_slot.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String id,uid;
                id=Id.getText().toString();
                uid=Uid.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url_base,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("RESPONSE FROM THE SERVER");
                                builder.setMessage("Response : "+response);
                                builder.setPositiveButton("..Ok..", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                      //  Id.setText("");
                                    }
                                });

                                AlertDialog alertDialog= builder.create();
                                alertDialog.show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(b_slot.this,"Error....",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){  @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("id",id);
                    return params;



                }};
                MySingleton.getInstance(b_slot.this).addToRequestQue(stringRequest);
            }
        });
Button button1=(Button)findViewById(R.id.bn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String id,uid;
                id=Id.getText().toString();
                uid=Uid.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url_base1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("RESPONSE FROM THE SERVER");
                                builder.setMessage("Response : "+response);
                                builder.setPositiveButton("..Ok..", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Id.setText("");
                                        Uid.setText("");
                                    }
                                });

                                AlertDialog alertDialog= builder.create();
                              alertDialog.show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(b_slot.this,"Error....",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){  @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("id",id);
                    params.put("uid",uid);
                    return params;



                }};
                MySingleton.getInstance(b_slot.this).addToRequestQue(stringRequest);
            }
        });





    }
}
