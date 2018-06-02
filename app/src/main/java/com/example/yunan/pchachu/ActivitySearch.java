package com.example.yunan.pchachu;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


import java.io.IOException;

public class ActivitySearch extends AppCompatActivity {
    private Place mPlace;
    private Button mSearchMinDateButton;
    private Button mSearchMaxDateButton;

    private AppBarLayout mAppBarLayout;
    private FloatingActionButton mFab;
    private FloatingActionButton mFabBottom;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private SearchTask mSearchTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PCcafe검색");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAppBarLayout = (AppBarLayout)findViewById(R.id.app_bar);

        //장소 검색
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.getView().setBackgroundColor(Color.parseColor("#FFFFFF"));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());

                /*String placeDetailsStr = place.getName() + "\n"
                        + place.getId() + "\n"
                        + place.getLatLng().toString() + "\n"
                        + place.getAddress() + "\n"
                        + place.getAttributions();
                txtPlaceDetails.setText(placeDetailsStr);*/

                mPlace = place;
            }

            @Override
            public void onError(Status status) {
                // Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
            }
        });


        mFab = (FloatingActionButton) findViewById(R.id.search_fab);
        mFabBottom = (FloatingActionButton) findViewById(R.id.search_fab_bottom);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
                //is Fully Expanded
                //if(verticalOffset == 0){}                }

                //is Being Expanded
                if(verticalOffset > -360){
                    mFabBottom.hide();
                }
                //is Being Contracted
                else{//is
                    mFabBottom.show();
                }
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppBarLayout.setExpanded(false);
                mFabBottom.show();

                if(mPlace == null){
                    return;
                }
                //검색 쿼리 날려서 리스트뷰에 출력하기 (Material Card 이용)
                String city = mPlace.getName().toString();
                mSearchTask = new SearchTask(city);
                mSearchTask.execute((Void) null);

            }
        });

        mFabBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppBarLayout.setExpanded(true);
                mFabBottom.hide();
            }
        });


        //Searching Result View
        View resultView = findViewById(R.id.include_result_view);
        //getLayoutInflater().inflate(R.layout.content_scrolling_search, null, false);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) resultView.findViewById(R.id.recycler_view);

        //RecyclerView divider height control // item(cardview)에 margin 넣으면 필요없음.
        /*mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private final int dividerHeight = 0;
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = dividerHeight;
            }
        });*/

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }



    public class SearchTask extends AsyncTask<Void, Void, Cafe> {
        private String mAddress;
        SearchTask(String address) {
            mAddress = address;
        }



        @Override
        protected Cafe doInBackground(Void... params) {
            //attempt authentication against a network service.

            //TODO: 쿼리 및 데이터 파싱
            /*
            ModuleCommunication mc = new ModuleCommunication();
            String searchResult = mc.QUERY("/api/v1/trips", mAddress, mCheckIn, mCheckOut);

            Cafe cafe = new Cafe();
            ObjectMapper mapper = new ObjectMapper();
            try {
                cafe = mapper.readValue(searchResult, Cafe.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return cafe;
            */


            return null;

        }

        @Override
        protected void onPostExecute(final Cafe cafe) {
            mSearchTask = null;
            //TODO: 결과 출력 (ListView, RecyclerView 등을 이용)
            /*
            RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), cafe);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            */
        }

        @Override
        protected void onCancelled() {
            mSearchTask = null;
        }

    }
}
