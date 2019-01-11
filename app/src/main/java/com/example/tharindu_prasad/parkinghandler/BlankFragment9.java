package com.example.tharindu_prasad.parkinghandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment9.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment9#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment9 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private Button button;
    private  Button button1,button2;
    private View view;
    TimePicker C,E;
    private EditText A,B,D,F;
    AlertDialog.Builder builder;
    private String server_url="https://benighted-places.000webhostapp.com/booking.php";
    private String server_url2="https://benighted-places.000webhostapp.com/r_final.php";
    //r_final
    // TODO: Rename and change types of parameters
    private int  mParam1;


    private OnFragmentInteractionListener mListener;

    public BlankFragment9() {
        // Required empty public constructor
    }

    public static BlankFragment9 newInstance(int param1) {
        BlankFragment9 fragment = new BlankFragment9();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       Integer total=mParam1+5;
        onButtonPressed(total.toString());
        view= inflater.inflate(R.layout.fragment_blank_fragment9, container, false);
   A=(EditText)view.findViewById(R.id.a);
        B=(EditText)view.findViewById(R.id.b);
        C=(TimePicker) view.findViewById(R.id.c);
        E=(TimePicker) view.findViewById(R.id.e);
        D=(EditText)view.findViewById(R.id.d);
        F=(EditText)view.findViewById(R.id.f);
      button1=(Button)view.findViewById(R.id.bn1);
        checkemail();
        checkmnum();
      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final String id,time_dura,time_begin,time_end,user_email,user_id;
              id=A.getText().toString();
              time_dura=B.getText().toString();


              int a=C.getCurrentHour();
              int b=C.getCurrentMinute();
              String s=a+":"+b;
              time_begin=s.toString();

              int c=E.getCurrentHour();
              int d=E.getCurrentMinute();
              String ss=c+":"+d;
              time_end=ss.toString();


              user_email=D.getText().toString();
              user_id=F.getText().toString();
              Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",user_email , null));
              emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Booking at the slot");
              emailIntent.putExtra(Intent.EXTRA_TEXT, "New parking slot has booked at slot id \n id: "+id+" \n from \n time begin"+time_begin+" to \n time end"+time_end+"\n for \n"+time_dura+"hours");
              startActivity(Intent.createChooser(emailIntent, "Send email..."));
              A.setText("");
              B.setText("");
              D.setText("");
              F.setText("");
          }
      });


        button2=(Button)view.findViewById(R.id.bn2);
        builder=new AlertDialog.Builder(view.getContext());


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id,time_dura,time_begin,time_end,user_email,user_id;
                id=A.getText().toString();
                time_dura=B.getText().toString();
                int a=C.getCurrentHour();
                int b=C.getCurrentMinute();
                String s=a+":"+b;
                time_begin=s.toString();

                int c=E.getCurrentHour();
                int d=E.getCurrentMinute();
                String ss=c+":"+d;
                time_end=ss.toString();

                user_email=D.getText().toString();
                user_id=F.getText().toString();


                if(id.equals("")||time_begin.equals("")||time_dura.equals("")||user_email.equals("")||user_id.equals("")){

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
                        params.put("b", time_dura);
                        params.put("c",time_begin);
                        params.put("d",user_email);
                        params.put("e",time_end);
                        params.put("f",user_id);
                        return params;


                    }



                    };
                    MySingleton.getInstance(view.getContext()).addToRequestQue(stringRequest);

                }
            }
        });










button=(Button)view.findViewById(R.id.bn);
        builder=new AlertDialog.Builder(view.getContext());


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final String id,time_dura,time_begin,time_end,user_email;
        id=A.getText().toString();
        time_dura=B.getText().toString();
        int a=C.getCurrentHour();
        int b=C.getCurrentMinute();
        String s=a+":"+b;
        time_begin=s.toString();

        int c=E.getCurrentHour();
        int d=E.getCurrentMinute();
        String ss=c+":"+d;
        time_end=ss.toString();

        user_email=D.getText().toString();



        if(id.equals("")||time_begin.equals("")||time_dura.equals("")||user_email.equals("")){

            builder.setTitle("Something went wrong.....");
            builder.setMessage("please fill all the fields...");
           displayAlert("input_error");
        }else {











            StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                              //  JSONArray jsonArray=new JSONArray(response);
                               // JSONObject jsonObject=jsonArray.getJSONObject(0);
                               // String code=jsonObject.getString("code");
                               // String message=jsonObject.getString("message");
                                builder.setTitle("server Response");
                                builder.setMessage("Response : " + response);
                                //builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       // Id1.setText("");
                                    }
                                });

                           AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("a", id);
                params.put("b", time_dura);
                params.put("c",time_begin);
                params.put("d",user_email);
                params.put("e",time_end);
                return params;


            }



            };
            MySingleton.getInstance(view.getContext()).addToRequestQue(stringRequest);

        }
    }
});

   return view;
    }
    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("input_error")){

                    A.setText("");
                  B.setText("");
                   D.setText("");
                   F.setText("");


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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String data) {
        if (mListener != null) {
            mListener.onFragmentInteraction(data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String data);
    }

    private void checkemail() {
        // TODO Auto-generated method stub

        D.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(D); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                   // valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid Email Address");
                   // valid_email = null;
                } else {
                   // valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence D) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(D)
                        .matches();
            }
        });

    }
    private void checkmnum(){
        // TODO Auto-generated method stub

        B.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                B.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        String r=B.getText().toString();
                        int value=Integer.parseInt(r);
                        if(value>12||value<0){
                            B.setError("10 digit no");
                        }
                    }
                });
            }
        });

    }


}
