package com.example.tharindu_prasad.parkinghandler;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tharindu_prasad.parkinghandler.data.SlotObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nipunu on 1/8/2018.
 */

public class SlotListRetrieveTask {
    Context context;
    ArrayList<SlotObject> arrayList = new ArrayList<>();
    String json_url = "https://benighted-places.000webhostapp.com/slot.php";

    public SlotListRetrieveTask(Context context) {

        this.context = context;
    }

    public ArrayList<Contact> getList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                SlotObject contact = new SlotObject(jsonObject.getInt("id"), jsonObject.getBoolean("availability"));
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
                Toast.makeText(context, "Error......", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);
        return null;
    }

}
