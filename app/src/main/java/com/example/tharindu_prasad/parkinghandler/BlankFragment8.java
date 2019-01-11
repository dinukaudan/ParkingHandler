package com.example.tharindu_prasad.parkinghandler;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment8#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment8 extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button button;
    AlertDialog.Builder builder;
    private View view;
    EditText Id;
    private Button but1;
    private Button button1;
    private String url_base = "https://benighted-places.000webhostapp.com/pmtr.php?";
    private  String server_url="https://benighted-places.000webhostapp.com/frees.php";
    public BlankFragment8() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment8.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment8 newInstance(String param1, String param2) {
        BlankFragment8 fragment = new BlankFragment8();
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
      view= inflater.inflate(R.layout.fragment_blank_fragment8, container, false);
        Button button=(Button)view.findViewById(R.id.bn2);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),Main4Activity.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

         but1 = (Button) view.findViewById(R.id.but1);
            Id=(EditText)view.findViewById(R.id.slot_id);
        builder=new AlertDialog.Builder(view.getContext());
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSlotDataForId();
            }
        }


        );
        button1=(Button)view.findViewById(R.id.bn10);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        final String id;
        id=Id.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("server Response");
                                builder.setMessage("Response:");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                        Toast.makeText(view.getContext(), "Error....", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //params.put("name", name);
                       // params.put("email", email);
                        params.put("id",id);
                        return params;


                    }

                };

                MySingleton.getInstance(view.getContext()).addToRequestQue(stringRequest);


            }});

        return view;
    }













    private void getSlotDataForId(){
        EditText slotId = (EditText) view.findViewById(R.id.slot_id);
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
                            email(response);
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
       // TextView avail=(TextView) view.findViewById(R.id.availability);
        TextView pmt=(TextView) view.findViewById(R.id.pmt);
        //TextView timeDura=(TextView) view.findViewById(R.id.time_dura);

        try {
           // avail.setText(response.getString("availability").equals("1")? "Available":"Occupied");

            String num1=response.getString("hourly_rate");
            String num2=response.getString("time_dura");
            int value=Integer.parseInt(num1)*Integer.parseInt(num2);
            pmt.setText(String.valueOf(value));
         //  pmt.setText(response.getString("hourly_rate"));
          //  timeDura.setText(response.getString("time_dura"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void email(JSONObject response){
        // TextView avail=(TextView) view.findViewById(R.id.availability);
        TextView pmt=(TextView) view.findViewById(R.id.pmt);
        //TextView timeDura=(TextView) view.findViewById(R.id.time_dura);

        try {
            // avail.setText(response.getString("availability").equals("1")? "Available":"Occupied");
            //String add=response.getString("user_email");
            String num1=response.getString("hourly_rate");
            String num2=response.getString("time_dura");
            String r=response.getString("user_email");
            String r2=response.getString("id");
            int value=Integer.parseInt(num1)*Integer.parseInt(num2);
            //pmt.setText(String.valueOf(value));
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", r, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "this is ur payment confirmation");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "u have paid Rs."+value+"for the slot"+r2+"from"+num2+"for"+num1+"rate");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            //  pmt.setText(response.getString("hourly_rate"));
            //  timeDura.setText(response.getString("time_dura"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }







}
