package com.example.tharindu_prasad.parkinghandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class user_data extends AppCompatActivity {
 Button button;
 Button button1;
    private String url_base = "https://benighted-places.000webhostapp.com/user_data.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

Button button=(Button)findViewById(R.id.button2);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getSlotDataForId();
    }
});



    }

    private void getSlotDataForId(){
        EditText slotId = (EditText) findViewById(R.id.slot_id);
        final String slotIdValue = slotId.getText().toString();

        String url = url_base + "id=" + slotIdValue;
        if(slotIdValue.isEmpty()){
            Toast.makeText(user_data.this,"Enter a Valid Slot Number",Toast.LENGTH_SHORT).show();
        }else{
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            updateUIWithSlotData(response);

                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Error downloading slotData",Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            MySingleton.getInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }
    }
    private void updateUIWithSlotData(JSONObject response){
        TextView avail=(TextView) findViewById(R.id.availability);
        TextView timBegin=(TextView) findViewById(R.id.time_begin);
        TextView timeDura=(TextView) findViewById(R.id.time_dura);
        TextView locid=(TextView) findViewById(R.id.loc_id);

        try {
            avail.setText(response.getString("availability").equals("1")? "Occupied":"Available");
            timBegin.setText(response.getString("time_begin"));
            timeDura.setText(response.getString("time_dura"));
            locid.setText(response.getString("loc_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

}}
