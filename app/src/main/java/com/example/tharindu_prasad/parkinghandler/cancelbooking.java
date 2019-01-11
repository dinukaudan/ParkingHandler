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

public class cancelbooking extends AppCompatActivity {
     private    Button button;
     private  Button button1;
     private EditText Id;
     private EditText Id1;
    private String url_base="https://benighted-places.000webhostapp.com/frees.php";
    private String url_base1="https://benighted-places.000webhostapp.com/confirm.php";

AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelbooking);

button=(Button)findViewById(R.id.bn);
Id=(EditText)findViewById(R.id.id);
builder=new AlertDialog.Builder(cancelbooking.this);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final String id;
        id = Id.getText().toString();

        if (id.equals("")) {

            builder.setTitle("Something went wrong.....");
            builder.setMessage("please fill all the id fields...");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Id1.setText("");
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_base,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            builder.setTitle("RESPONSE FROM THE SERVER");
                            builder.setMessage("Response : " + response);
                            builder.setPositiveButton("..Ok..", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Id.setText("");
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(cancelbooking.this, "Error....", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("id", id);
                    return params;


                }
            };
            MySingleton.getInstance(cancelbooking.this).addToRequestQue(stringRequest);
        }
    }});


       button1=(Button)findViewById(R.id.bn1);
       Id1=(EditText)findViewById(R.id.id1);
      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final String id;
              id=Id1.getText().toString();


              if(id.equals("")){

                  builder.setTitle("Something went wrong.....");
                  builder.setMessage("please fill all the id fields...");
                  builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Id1.setText("");
                      }
                  });

                  AlertDialog alertDialog = builder.create();
                  alertDialog.show();


              }else {
                  StringRequest stringRequest = new StringRequest(Request.Method.POST, url_base1,
                          new Response.Listener<String>() {
                              @Override
                              public void onResponse(String response) {
                                  builder.setTitle("RESPONSE FROM THE SERVER");
                                  builder.setMessage("Response : " + response);
                                  builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {
                                          Id1.setText("");
                                      }
                                  });

                                  AlertDialog alertDialog = builder.create();
                                  alertDialog.show();

                              }
                          }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          Toast.makeText(cancelbooking.this, "Error....", Toast.LENGTH_SHORT).show();
                          error.printStackTrace();
                      }
                  }) {
                      @Override
                      protected Map<String, String> getParams() throws AuthFailureError {
                          Map<String, String> params = new HashMap<>();

                          params.put("id", id);
                          return params;


                      }


                  };
                  MySingleton.getInstance(cancelbooking.this).addToRequestQue(stringRequest);
              }      }
      });


    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("input_error")){

                   // A.setText("");
                  //  B.setText("");
                  //  D.setText("");
                  //  F.setText("");


                }
                else {
                    //    A.setText("");
                    //  B.setText("");
                    //     D.setText("");
                    //    F.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();




    }


}
