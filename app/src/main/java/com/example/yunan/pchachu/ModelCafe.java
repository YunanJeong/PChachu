package com.example.yunan.pchachu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yunan on 2018-06-06.
 */

public class ModelCafe {
    private String mAddress;

    private Map<String,Object> trip = new HashMap<String,Object>();
    private List<Map<String,Object>> trips = new ArrayList<Map<String,Object>>();

    public Map<String,Object> getTrip(){
        return trip;
    }
    public List getTrips(){
        return trips;
    }
}
