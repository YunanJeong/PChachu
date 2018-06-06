package com.example.yunan.pchachu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by yunan on 2017-05-28.
 */

public class ModelRecyclerAdapterEvent extends RecyclerView.Adapter<ModelRecyclerAdapterEvent.ViewHolder>{
    Context context;
    ModelCafe cafe;

    public ModelRecyclerAdapterEvent(Context context, Object obj) {
        this.context = context;
        if(obj instanceof ModelCafe){
            cafe = (ModelCafe)obj;
        }
    }

    @Override
    public ModelRecyclerAdapterEvent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_content_event, parent, false);
        return new ViewHolder(v);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final HashMap<String,Object> temp;

        temp = (HashMap<String,Object>)cafe.getEvents().get(position);

        holder.eventNameView.setText(temp.get("Pc_event_name").toString());
        holder.eventPriceView.setText(temp.get("Pc_event_award").toString());

    }

    @Override
    public int getItemCount() {
        return this.cafe.getEvents().size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView eventNameView;
        TextView eventPriceView;



        public ViewHolder(View v) {
            super(v);

            cardView = (CardView) v.findViewById(R.id.card_view);
            eventNameView = (TextView) cardView.findViewById(R.id.event_name);
            eventPriceView = (TextView) cardView.findViewById(R.id.event_reward);


        }
    }



}
