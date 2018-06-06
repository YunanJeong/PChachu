package com.example.yunan.pchachu;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JacksonParsingTest {

    ModelAddressParser ap = new ModelAddressParser();

    @Test
    public void testJacksonParsing() {

        String searchResult = "[{\"Pc_id\":4003,\"Pc_name\":\"갤럭시 PC방 아주대점\",\"Pc_address1\":\"경기도\",\"Pc_address2\":\"수원시\",\"Pc_address3\":\"우만동\",\"Pc_address4\":\"아주로47번길 15\"},{\"Pc_id\":4004,\"Pc_name\":\"스토리온 PC방 아주대점\",\"Pc_address1\":\"경기도\",\"Pc_address2\":\"수원시\",\"Pc_address3\":\"우만동\",\"Pc_address4\":\"아주로 47번길 6\"}]";
        //ModelCafe cafe = new ModelCafe();
        List<Map<String,Object>> cafes=null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            //cafe = mapper.readValue(searchResult, ModelCafe.class);
            cafes = mapper.readValue(searchResult, new TypeReference<List<Map<String,Object>>>(){});
        //    Log.e(">>>>",cafes.get(1).get("Pc_address1").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.e(">>>>",cafes.get(1).get("Pc_address1").toString());
        assertEquals("경기도",cafes.get(1).get("Pc_address1").toString());
    }


}