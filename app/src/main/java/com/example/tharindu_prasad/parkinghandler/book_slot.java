package com.example.tharindu_prasad.parkinghandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class book_slot extends AppCompatActivity {
    AlertDialog.Builder builder;
    private String server_url2="https://benighted-places.000webhostapp.com/slot_book.php";
EditText A;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);




       Button button1=(Button)findViewById(R.id.button6);
        builder=new AlertDialog.Builder(getApplicationContext());
       A=(EditText)findViewById(R.id.a);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id;
                id=A.getText().toString();



                if(id.equals("")){

                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("please fill all the fields...");
                    displayAlert("input_error");
                }else {











                    StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url2,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");
                                        String message=jsonObject.getString("message");
                                        builder.setTitle("server Response");
                                        builder.setMessage(message);
                                        //builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        displayAlert(code);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }




                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){@Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("a", id);

                        return params;


                    }



                    };
                    MySingleton.getInstance(book_slot.this).addToRequestQue(stringRequest);

                }
            }
        });







    }
    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("input_error")){

                    A.setText("");


                }
                else {
                    A.setText("");

                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();




    }

}
