package com.example.tharindu_prasad.parkinghandler;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.tharindu_prasad.parkinghandler.data.SlotObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment6 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private String url_base = "https://benighted-places.000webhostapp.com/slotr.php?";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     private   Button but1;






    public BlankFragment6() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment6.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment6 newInstance(String param1, String param2) {
        BlankFragment6 fragment = new BlankFragment6();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_blank_fragment6, container, false);

        Button button3 = (Button) view.findViewById(R.id.button4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), user_data.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });
        Button button1 = (Button) view.findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), b_slot.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });



        Button but2 = (Button) view.findViewById(R.id.but2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Main4Activity.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });


        Button but1=(Button)view.findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),slots.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        Button button2= (Button)view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSlotDataForId();
            }
        });

        return view;
    }

    private void getSlotDataForId(){
        TextView slotId = (TextView)view.findViewById(R.id.slot_id);
        final String slotIdValue = slotId.getText().toString();

        String url = url_base + "id=" + slotIdValue;
        if(slotIdValue.isEmpty()){
            Toast.makeText(view.getContext(),"Enter a Valid Slot Number",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(view.getContext(),"Error downloading slotData",Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            MySingleton.getInstance(view.getContext()).addToRequestQue(jsonObjectRequest);
        }
    }

    private void updateUIWithSlotData(JSONObject response){
        TextView avail=(TextView) view.findViewById(R.id.availability);
        TextView timBegin=(TextView) view.findViewById(R.id.time_begin);
        TextView timeDura=(TextView) view.findViewById(R.id.time_dura);
        TextView Vno=(TextView)view.findViewById(R.id.vno);
        TextView Rid=(TextView)view.findViewById(R.id.rid);
        try {
            avail.setText(response.getString("availability").equals("1")? "Occupied":"Available");
            timBegin.setText(response.getString("time_begin"));
            timeDura.setText(response.getString("time_dura"));
            Vno.setText(response.getString("vno"));
            Rid.setText(response.getString("rid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }



}
