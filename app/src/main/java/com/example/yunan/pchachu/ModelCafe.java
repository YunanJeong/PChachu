package com.example.yunan.pchachu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yunan on 2018-06-06.
 */

public class ModelCafe {


    private List<Map<String,Object>> mCafes = null;
    private List<Map<String,Object>> mFoods = null;
    private List<Map<String,Object>> mEvents = null;

    public void setCafes(List<Map<String,Object>> cafes){
        mCafes = cafes;
    }
    public List<Map<String,Object>> getCafes(){
        return mCafes;
    }

    public void setFoods(List<Map<String,Object>> foods){
        mFoods = foods;
    }
    public List<Map<String,Object>> getFoods(){
        return mFoods;
    }

    public void setEvents(List<Map<String,Object>> events){
        mEvents = events;
    }
    public List<Map<String,Object>> getEvents(){
        return mEvents;
    }


}
