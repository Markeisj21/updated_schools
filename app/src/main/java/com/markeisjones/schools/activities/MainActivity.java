package com.markeisjones.schools.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.markeisjones.schools.R;
import com.markeisjones.schools.adapters.RecyclerViewAdapter;
import com.markeisjones.schools.model.School_Info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL="https://data.cityofnewyork.us/resource/734v-jeq5.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<School_Info> mSchool_info;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSchool_info = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
        
        
    }

    private void jsonrequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++){

                try {

                    jsonObject = response.getJSONObject(i);
                    School_Info school_info = new School_Info();
                    school_info.setDbn(jsonObject.getString("dbn"));
                    school_info.setNum_of_sat_test_takers(jsonObject.getString("num_of_sat_test_takers"));
                    school_info.setSat_critical_reading_avg_score(jsonObject.getString("sat_critical_reading_avg_score"));
                    school_info.setSat_math_avg_score(jsonObject.getString("sat_math_avg_score"));
                    school_info.setSat_writing_avg_score(jsonObject.getString("sat_writing_avg_score"));
                    school_info.setSchool_name(jsonObject.getString("school_name"));
                    mSchool_info.add(school_info);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
            setupRV(mSchool_info);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setupRV(List<School_Info> mSchool_info) {

        RecyclerViewAdapter deezAdapter = new RecyclerViewAdapter(this,mSchool_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(deezAdapter);

    }
}
