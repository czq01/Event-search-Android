package com.myapplication.myapplication.detailpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.myapplication.myapplication.R;
import com.myapplication.myapplication.ViewPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TabActivity extends AppCompatActivity {
    private String url;
    private String name;
    private RequestQueue req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        req = Volley.newRequestQueue(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.app_name);
        onViewCreated();
    }

    public void onViewCreated() {
        url = getIntent().getStringExtra("url");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        onRecieveDetails(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Query Error
                        Log.d("onErr", error.toString());
                    }
                });
        // Add the request to the RequestQueue.
        req.add(stringRequest);

    }

    public void onRecieveDetails(String response) {
        setTabs(response);
//        JSONObject event, venue, artists;
//        try {
//            JSONArray res = new JSONArray(response);
//            event = res.getJSONObject(0);
//            venue = res.getJSONObject(1);
//            artists = res.getJSONObject(2);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void setTabs(String jsonResp) {
        Bundle bundle = new Bundle();
        bundle.putString("return", jsonResp);
        DetailPageAdapter adapter = new DetailPageAdapter(this, bundle);
        TabLayout tabs = findViewById(R.id.detail_tabs);
        ViewPager2 viewpage = findViewById(R.id.detail_viewPager);
        viewpage.setAdapter(adapter);
        Log.d("setTab", "Ok");
        name = getIntent().getStringExtra("name");

        new TabLayoutMediator(tabs, viewpage, (tab, pos) -> {
            View customView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            TextView tabTitle = customView.findViewById(R.id.tab_title);
            ImageView tabIcon = customView.findViewById(R.id.tab_icon);
            switch (pos) {
                case 0:
                    tabTitle.setText("DETAILS");
                    tabIcon.setImageResource(R.drawable.outline_info_24);
                    break;
                case 1:
                    tabTitle.setText("ARTISTS");
                    tabIcon.setImageResource(R.drawable.outline_mic_external_on_24);
                    break;
                case 2:
                    tabTitle.setText("VENUE");
                    tabIcon.setImageResource(R.drawable.baseline_map_24);
                    break;
            }
            tab.setCustomView(customView);
        }).attach();

//        tabs.getTabAt(0).setIcon();
//        tabs.getTabAt(1).setIcon();
//        tabs.getTabAt(2).setIcon();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}