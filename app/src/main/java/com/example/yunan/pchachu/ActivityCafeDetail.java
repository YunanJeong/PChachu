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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_detail);

        final HashMap<String,Object> cafeTemp = (HashMap<String,Object>)  getIntent().getSerializableExtra("Cafe");

        setTitle(cafeTemp.get("address").toString());

        TextView metroTextView = (TextView) findViewById(R.id.detail_address_metropolice);
        TextView cityTextView = (TextView) findViewById(R.id.detail_address_city);
        TextView townTextView = (TextView) findViewById(R.id.detail_address_town);
        TextView extraTextView = (TextView) findViewById(R.id.detail_address_extra);
        metroTextView.setText(cafeTemp.get("pc_address1").toString());
        cityTextView.setText(cafeTemp.get("pc_address2").toString());
        townTextView.setText(cafeTemp.get("pc_address3").toString());
        extraTextView.setText(cafeTemp.get("pc_address4").toString());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mMenuRecyclerView = (RecyclerView) findViewById(R.id.detail_recycle_menu) ;
        mMenuRecyclerView.setHasFixedSize(true);
        mMenuRecyclerView.setLayoutManager(linearLayoutManager);


        mEventRecyclerView = (RecyclerView) findViewById(R.id.detail_recycle_event) ;
        mEventRecyclerView.setHasFixedSize(true);
        mEventRecyclerView.setLayoutManager(linearLayoutManager);


        mSearchTask = new SearchDetailTask(cafeTemp.get("pc_id").toString());
        mSearchTask.execute((Void) null);

     /*   mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) resultView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);*/
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

            //cafe detail 쿼리
            String searchResult = mc.QUERYDetail("/api/v1/trips", mCafeId);

            //cafe detail Response 데이터 파싱
            List<Map<String,Object>> cafes=null;
            ObjectMapper mapper = new ObjectMapper();
            try {
                cafes = mapper.readValue(searchResult, new TypeReference<List<Map<String,Object>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }



            //modelCafe.setFoods();
            //modelCafe.setEvents();
            modelCafe.setCafes(cafes);



            return modelCafe;

        }

        @Override
        protected void onPostExecute(final ModelCafe modelCafe) {
            mSearchTask = null;

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
