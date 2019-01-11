package com.example.tharindu_prasad.parkinghandler;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Angel on 2017-12-22.
 */

public class BackgroundTask {

Context context;
    ArrayList<Contact> arrayList=new ArrayList<>();
    String json_url="https://benighted-places.000webhostapp.com/array.php";
    public BackgroundTask(Context context){

        this.context=context;
    }

    public ArrayList<Contact> getList(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, json_url,(String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                 int count=0;
                        while (count<response.length()){
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                Contact contact=new Contact(jsonObject.getString("Name"),jsonObject.getString("Email"));
                                arrayList.add(contact);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error......",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);
        return null;
    }

}
