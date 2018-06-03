package com.example.yunan.pchachu;

public class ModelAddressParser {

    private String mAddress;
    private String mNation; //국가
    private String mMetropolice; //광역 시도
    private String mCity; //기초 시군구
    private String mTown; // 읍면동

    ModelAddressParser(){
    }

    ModelAddressParser(String address){
        parseAddress(address);
    }

    public void parseAddress(String address){
        if(address==null) return;

        mAddress = address;

        String[] mAddrs = mAddress.split(" ");
        if(mAddrs.length>0) mNation = mAddrs[0];
        if(mAddrs.length>1) mMetropolice = mAddrs[1];
        if(mAddrs.length>2) mCity = mAddrs[2];
        if(mAddrs.length>3) {
            mTown = mAddrs[3];
            if (mTown.contains("구")) mTown = null;
        }
        if(mAddrs.length>4) mTown = mAddrs[4];

    }

    public String getNation(){
        return mNation;
    }

    public String getMetropolice(){
        return mMetropolice;
    }

    public String getCity(){
        return mCity;
    }

    public String getTown(){
        return mTown;
    }
}
