package com.example.tharindu_prasad.parkinghandler;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.egl.EGLDisplay;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment7 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    Button button,button2;
    EditText Name,Id;
    TimePicker Email;
    public String server_url="https://benighted-places.000webhostapp.com/ljhfs.php";
    private String server_url1="https://benighted-places.000webhostapp.com/res_ex.php";

    AlertDialog.Builder builder;





    public BlankFragment7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment7.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment7 newInstance(String param1, String param2) {
        BlankFragment7 fragment = new BlankFragment7();
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
       view= inflater.inflate(R.layout.fragment_blank_fragment7, container, false);
        button=(Button)view.findViewById(R.id.bn);
        Name=(EditText)view.findViewById(R.id.name);
        Email=(TimePicker) view.findViewById(R.id.email);
        Id=(EditText)view.findViewById(R.id.id);



        Button but1=(Button)view.findViewById(R.id.bn1);



        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),cancelbooking.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        Button but2=(Button)view.findViewById(R.id.bn2);
        builder=new AlertDialog.Builder(view.getContext());
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,email,id;
                name=Name.getText().toString();
                int a=Email.getCurrentHour();
                int b=Email.getCurrentMinute();
                String s=a+":"+b;
                email=s.toString();

                id=Id.getText().toString();

                if(id.equals("")||email.equals("")|| name.equals("")){

                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("please fill all the fields...");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Id.setText("");
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("server Response");
                                builder.setMessage("Response:");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Name.setText("");
                                        //Email.setText("");
                                        Name.setText("");
                                     // Id.setText("");
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
                        params.put("name", name);
                        params.put("email", email);
                        params.put("id",id);
                        return params;


                    }

                };

                MySingleton.getInstance(view.getContext()).addToRequestQue(stringRequest);


            }}});





        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),cancelbooking.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });


        builder=new AlertDialog.Builder(view.getContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,email,id;
                name=Name.getText().toString();
                int a=Email.getCurrentHour();
                int b=Email.getCurrentMinute();
                String s=a+":"+b;
                email=s.toString();

                id=Id.getText().toString();
                if(id.equals("")||email.equals("")|| name.equals("")){

                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("please fill all the fields...");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Id.setText("");
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }else {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                builder.setTitle("server Response");
                                builder.setMessage("Response:");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Name.setText("");
                                        //Email.setText("");
                                       // Id.setText("");
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
                        params.put("name", name);
                        params.put("email", email);
                        params.put("id",id);
                        return params;


                    }

                };

                MySingleton.getInstance(view.getContext()).addToRequestQue(stringRequest);


            }}});
       return view;
    }

}
