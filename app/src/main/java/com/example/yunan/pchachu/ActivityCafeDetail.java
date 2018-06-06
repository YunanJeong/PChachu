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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityCafeDetail extends AppCompatActivity {

    private SearchDetailTask mSearchTask;
    private RecyclerView mEventRecyclerView;
    private RecyclerView mMenuRecyclerView;
    private TextView mSeatsView;
    private TextView mSpecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_detail);

        final HashMap<String,Object> cafeTemp = (HashMap<String,Object>)  getIntent().getSerializableExtra("Cafe");

        setTitle(cafeTemp.get("Pc_name").toString());

        TextView metroTextView = (TextView) findViewById(R.id.detail_address_metropolice);
        TextView cityTextView = (TextView) findViewById(R.id.detail_address_city);
        TextView townTextView = (TextView) findViewById(R.id.detail_address_town);
        TextView extraTextView = (TextView) findViewById(R.id.detail_address_extra);
        metroTextView.setText(cafeTemp.get("Pc_address1").toString());
        cityTextView.setText(cafeTemp.get("Pc_address2").toString());
        townTextView.setText(cafeTemp.get("Pc_address3").toString());
        extraTextView.setText(cafeTemp.get("Pc_address4").toString());


        LinearLayoutManager linearLayoutManager_menu = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager_menu.setOrientation(LinearLayoutManager.VERTICAL);
        mMenuRecyclerView = (RecyclerView) findViewById(R.id.detail_recycle_menu) ;
        mMenuRecyclerView.setHasFixedSize(true);
        mMenuRecyclerView.setLayoutManager(linearLayoutManager_menu);


        LinearLayoutManager linearLayoutManager_event = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager_event.setOrientation(LinearLayoutManager.VERTICAL);
        mEventRecyclerView = (RecyclerView) findViewById(R.id.detail_recycle_event) ;
        mEventRecyclerView.setHasFixedSize(true);
        mEventRecyclerView.setLayoutManager(linearLayoutManager_event);


        mSearchTask = new SearchDetailTask(cafeTemp.get("Pc_id").toString());
        mSearchTask.execute((Void) null);


    }

    public class SearchDetailTask extends AsyncTask<Void, Void, ModelCafe> {
        private String mCafeId;

        SearchDetailTask(String cafeId) {
            mCafeId = cafeId;
        }



        @Override
        protected ModelCafe doInBackground(Void... params) {
            //attempt authentication against a network service.
            ModelCafe modelCafe = new ModelCafe();
            ModelCommunication mc = new ModelCommunication();
            ObjectMapper mapper = new ObjectMapper();

            //cafe detail 쿼리
            String searchResult = mc.QUERYDetail("/food/get2", mCafeId);

            //cafe detail Response 데이터 파싱
            List<Map<String,Object>> cafes=null;

            try {
                cafes = mapper.readValue(searchResult, new TypeReference<List<Map<String,Object>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }


            //foodmenu 쿼리
            String menuResult = mc.QUERYDetail("/food/get4", mCafeId);

            //menu Response 데이터 파싱
            List<Map<String,Object>> foods=null;
            try {
                foods = mapper.readValue(menuResult, new TypeReference<List<Map<String,Object>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }

            //foodmenu 쿼리
            String eventResult = mc.QUERYDetail("/food/get3", mCafeId);

            //cafe detail Response 데이터 파싱
            List<Map<String,Object>> events=null;
            try {
                events = mapper.readValue(eventResult, new TypeReference<List<Map<String,Object>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }

            modelCafe.setCafes(cafes);
            modelCafe.setFoods(foods);
            modelCafe.setEvents(events);


            return modelCafe;

        }

        @Override
        protected void onPostExecute(final ModelCafe modelCafe) {
            mSearchTask = null;
            mSeatsView =  (TextView) findViewById(R.id.detail_facility_seats);
            mSpecView =  (TextView) findViewById(R.id.detail_facility_spec);

            //결과출력
            mSeatsView.setText(modelCafe.getCafes().get(0).get("Pc_seat").toString()+"개 좌석");
            mSpecView.setText(modelCafe.getCafes().get(0).get("Pc_spec").toString());

            //결과 출력 (ListView, RecyclerView 등을 이용)
            ModelRecyclerAdapterMenu adapterMenu = new ModelRecyclerAdapterMenu(getApplicationContext(), modelCafe);
            mMenuRecyclerView.setAdapter(adapterMenu);
            adapterMenu.notifyDataSetChanged();

            ModelRecyclerAdapterEvent adapterEvent = new ModelRecyclerAdapterEvent(getApplicationContext(), modelCafe);
            mEventRecyclerView.setAdapter(adapterEvent);
            adapterEvent.notifyDataSetChanged();

        }

        @Override
        protected void onCancelled() {
            mSearchTask = null;
        }

    }

}
