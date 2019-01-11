package com.example.tharindu_prasad.parkinghandler;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.tharindu_prasad.parkinghandler.data.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private Button but1;
    private View view;
    private String url_base = "https://benighted-places.000webhostapp.com/a.php?";
    Button button2;
    Button button1;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);




        Button but1 = (Button) view.findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Main4Activity.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });
        getUserData();
        return view;
    }

    private void getUserData() {
        String url = url_base + "name=" + UserData.getSharedInstance().getName();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        updateUserData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(), "Error downloading userData", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        MySingleton.getInstance(view.getContext()).addToRequestQue(jsonObjectRequest);
    }

    private void updateUserData(JSONObject response) {
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView email = (TextView) view.findViewById(R.id.email);
        TextView mobile = (TextView) view.findViewById(R.id.mobile);
        ImageView profileImage = (ImageView) view.findViewById(R.id.prof_pic);

        try {
            name.setText(response.getString("Name1"));
            email.setText(response.getString("Email1"));
            mobile.setText(response.getString("Mobile"));
            DownloadImageTask imageTask = new DownloadImageTask(profileImage,"https://static.pexels.com/photos/40183/birthday-cake-cake-birthday-cupcakes-40183.jpeg");
            imageTask.execute(null,null,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String url;

        public DownloadImageTask(ImageView bmImage, String url) {
            this.bmImage = bmImage;
            this.url = url;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = url;
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
