package com.example.tharindu_prasad.parkinghandler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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

public class slots extends AppCompatActivity {
    private static ArrayList<SlotObject> slotList = new ArrayList<SlotObject>();
    Button button;
    Context context;
    ArrayList<SlotObject> arrayList = new ArrayList<>();
    String json_url = "https://benighted-places.000webhostapp.com/listr.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        this.context = getApplicationContext();
        getSlotList();
    }

    private void populateTable(){
        TableLayout table = (TableLayout)slots.this.findViewById(R.id.slot_table);

        try{
            TableRow row = (TableRow) LayoutInflater.from(slots.this).inflate(R.layout.slot_row, null);

            TextView slotNumber = ((TextView)row.findViewById(R.id.slot_number));
            slotNumber.setText("Slot Number");
            slotNumber.setTextColor(Color.BLACK);
            slotNumber.setTypeface(null, Typeface.BOLD);
            slotNumber.setTextSize(18);
            slotNumber.setPadding(50, 5, 25, 5);

            TextView slotAvail = ((TextView)row.findViewById(R.id.slot_avail));
            slotAvail.setText("Availability");
            slotAvail.setTextColor(Color.BLACK);
            slotAvail.setTypeface(null, Typeface.BOLD);
            slotAvail.setTextSize(18);
            slotAvail.setPadding(25, 5, 50, 5);
            table.addView(row);

            int index = 0;
            for(SlotObject slot:slotList)
            {
                row = (TableRow) LayoutInflater.from(slots.this).inflate(R.layout.slot_row, null);

                slotNumber = ((TextView)row.findViewById(R.id.slot_number));
                slotNumber.setText(slot.getId()+"");
                slotNumber.setTextColor(Color.BLACK);
                slotNumber.setTextSize(18);
                slotNumber.setPadding(50, 5, 25, 5);

                slotAvail = ((TextView)row.findViewById(R.id.slot_avail));
                slotAvail.setText(slot.isAvailability()? "Available":"Occupied");
                slotAvail.setTextColor(Color.BLACK);
                slotAvail.setTextSize(18);
                slotAvail.setPadding(25, 5, 50, 5);
                table.addView(row);
                index++;

                if(index%2 == 1){
                    slotAvail.setBackgroundColor(Color.rgb(190,190,190));
                    slotNumber.setBackgroundColor(Color.rgb(190,190,190));
                }
            }
        } catch (Exception e){
            System.out.println("Error populating data"+e.getMessage());
        }
        table.requestLayout();
    }

    static {
        slotList.add(new SlotObject(34213,true));
        slotList.add(new SlotObject(13567,true));
        slotList.add(new SlotObject(34256,false));
        slotList.add(new SlotObject(16723,true));
        slotList.add(new SlotObject(67834,false));
        slotList.add(new SlotObject(34125,true));
        slotList.add(new SlotObject(45213,false));
        slotList.add(new SlotObject(13421,true));
        slotList.add(new SlotObject(11234,true));
        slotList.add(new SlotObject(23141,false));
    }

    public ArrayList<Contact> getSlotList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        slotList = new ArrayList<>();
                        while (count < response.length()) {
                            try {
                                boolean availbility = false;
                                JSONObject jsonObject = response.getJSONObject(count);
                                if (jsonObject.getString("availability").equals("1")) {
                                    availbility = true;
                                } else {
                                    availbility = false;
                                }
                                SlotObject slot = new SlotObject(jsonObject.getInt("id"), availbility);
                                slotList.add(slot);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        populateTable();
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
