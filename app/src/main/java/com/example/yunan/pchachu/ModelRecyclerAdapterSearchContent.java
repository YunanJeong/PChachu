package com.example.yunan.pchachu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by yunan on 2017-05-28.
 */

public class ModelRecyclerAdapterSearchContent extends RecyclerView.Adapter<ModelRecyclerAdapterSearchContent.ViewHolder>{
    Context context;
    ModelCafe mCafe;

    public ModelRecyclerAdapterSearchContent(Context context, Object obj) {
        this.context = context;
        if(obj instanceof ModelCafe){
            mCafe = (ModelCafe)obj;
        }
    }

    @Override
    public ModelRecyclerAdapterSearchContent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_content_search, parent, false);
        return new ViewHolder(v);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final HashMap<String,Object> temp;
        String address;

        temp = (HashMap<String,Object>) (mCafe.getCafes().get(position));

        address = temp.get("Pc_address1").toString() + " " + temp.get("Pc_address2").toString() +" "+ temp.get("Pc_address3").toString();
        holder.titleView.setText(temp.get("Pc_name").toString());
        holder.addressView.setText(address);

        //card item selected
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ActivityCafeDetail.class);
                //Send selected item information to ActivityCafeDetail
                intent.putExtra("Cafe",temp);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mCafe.getCafes().size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView titleView;
        TextView addressView;



        public ViewHolder(View v) {
            super(v);

            cardView = (CardView) v.findViewById(R.id.card_view);
            titleView = (TextView) cardView.findViewById(R.id.title_cafename);
            addressView = (TextView) cardView.findViewById(R.id.cafe_address);


        }
    }



}
