package com.example.amine_omayma_exam_m1_iibdcc_23;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;


public class IPFinderActivity extends AppCompatActivity {
    EditText ipAddress ;
    String IP_ADDRESS ;
    Button show, showMap ;
    LinearLayout container ;
    List<String> infoList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipfinder);

        ipAddress = findViewById(R.id.ipText);
        container = findViewById(R.id.container);
        show = findViewById(R.id.showButton);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 IP_ADDRESS = ipAddress.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(IPFinderActivity.this);
                    String url = "https://ipinfo.io/" +IP_ADDRESS+ "/geo";
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String city = jsonObject.getString("city");
                                        String region = jsonObject.getString("region");
                                        String country = jsonObject.getString("country");
                                        infoList.add("City : " + city);
                                        infoList.add("Region : " + region);
                                        infoList.add("Country : " + country);

                                        String LatLand = jsonObject.getString("loc");
                                        infoList.forEach(s -> {
                                            TextView toAdd = new TextView(IPFinderActivity.this);
                                            toAdd.setText(s);
                                            toAdd.setTextSize(18);
                                            toAdd.setTypeface(null, Typeface.BOLD);
                                            toAdd.setBackgroundResource(R.drawable.edit_text_style);
                                            toAdd.setTextColor(Color.parseColor("#3CB3A8"));
                                            //toAdd.setBackgroundColor(Color.parseColor("#3CB3A8"));
                                            toAdd.setPadding(20, 20, 20, 20);
                                            toAdd.setGravity(Gravity.CENTER);
                                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                            );
                                            layoutParams.setMargins(0, 0, 0, 20);
                                            toAdd.setLayoutParams(layoutParams);
                                            container.addView(toAdd);
                                        });
                                        Button MapBtn = new Button(IPFinderActivity.this);
                                        MapBtn.setText("Show Map");
                                        MapBtn.setPadding(0, 30, 0, 30);
                                        MapBtn.setBackgroundResource(R.drawable.button_style);
                                        MapBtn.setTextColor(Color.parseColor("#FFFFFFFF"));
                                        container.addView(MapBtn);
                                        MapBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent map = new Intent(v.getContext(), MapsActivity.class);
                                                map.putExtra("LatLand", LatLand);
                                                startActivity(map);
                                            }
                                        });
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error);
                        }
                    });
                    requestQueue.add(request);

            }
        });
    }

}
