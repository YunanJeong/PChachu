package com.example.yunan.pchachu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yunan on 2018-06-06.
 */

public class ModelCafe {


    //private Map<String,Object> trip = new HashMap<String,Object>();
    /*public Map<String,Object> getTrip(){
        return trip;
    }*/

    private List<Map<String,Object>> mCafes = null;


    public void setCafes(List<Map<String,Object>> cafes){
        mCafes = cafes;
    }
    public List getCafes(){
        return mCafes;
    }


}
