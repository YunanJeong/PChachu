package com.example.yunan.pchachu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityCafeDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_detail);

        final HashMap<String,Object> tripTemp = (HashMap<String,Object>)  getIntent().getSerializableExtra("Trip");
        final HashMap<String,Object> ownerTemp = (HashMap<String,Object>) tripTemp.get("owner");
        ArrayList<HashMap<String,Object>> membersTemp = (ArrayList<HashMap<String,Object>>) tripTemp.get("members");

        setTitle(tripTemp.get("address").toString());

        TextView metroTextView = (TextView) findViewById(R.id.detail_address_metropolice);
        TextView cityTextView = (TextView) findViewById(R.id.detail_address_city);
        TextView townTextView = (TextView) findViewById(R.id.detail_address_town);

        metroTextView.setText(tripTemp.get("pc_address1").toString());
        cityTextView.setText(tripTemp.get("pc_address2").toString());
        townTextView.setText(tripTemp.get("pc_address3").toString());





        TextView schoolChip = (TextView) findViewById(R.id.detail_host_profile_school);
        TextView jobChip = (TextView) findViewById(R.id.detail_host_profile_job);
        TextView localeChip = (TextView) findViewById(R.id.detail_host_profile_locale);
        TextView countryChip = (TextView) findViewById(R.id.detail_host_profile_country);

        schoolChip.setText(ownerTemp.get("school").toString());
        jobChip.setText(ownerTemp.get("job").toString());
        localeChip.setText(ownerTemp.get("locale").toString());
        countryChip.setText(ownerTemp.get("country").toString());




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView eventRecyclerView = (RecyclerView) findViewById(R.id.detail_recycle_event) ;
        eventRecyclerView.setHasFixedSize(true);
        eventRecyclerView.setLayoutManager(linearLayoutManager);
        ModelRecyclerAdapterEvent adapter = new ModelRecyclerAdapterEvent(getApplicationContext(), membersTemp);
        eventRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
