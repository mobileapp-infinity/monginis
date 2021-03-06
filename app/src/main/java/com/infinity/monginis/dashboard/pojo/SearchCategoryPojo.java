package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchCategoryPojo {

    @SerializedName("TOTAL")
    @Expose
    private Integer tOTAL;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("RECORDS")
    @Expose
    private RECORDS rECORDS;

    public Integer getTOTAL() {
        return tOTAL;
    }

    public void setTOTAL(Integer tOTAL) {
        this.tOTAL = tOTAL;
    }

    public String getMESSAGE() {
        return mESSAGE;
    }

    public void setMESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    public RECORDS getRECORDS() {
        return rECORDS;
    }

    public void setRECORDS(RECORDS rECORDS) {
        this.rECORDS = rECORDS;
    }

    public class RECORDS {

        @SerializedName("item_json")
        @Expose
        private List<ItemJson> itemJson = null;
        @SerializedName("cust_json")
        @Expose
        private List<CustJson> custJson = null;

        public List<ItemJson> getItemJson() {
            return itemJson;
        }

        public void setItemJson(List<ItemJson> itemJson) {
            this.itemJson = itemJson;
        }

        public List<CustJson> getCustJson() {
            return custJson;
        }

        public void setCustJson(List<CustJson> custJson) {
            this.custJson = custJson;
        }

    }

    public class ItemJson {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("itm_name")
        @Expose
        private String itmName;
        @SerializedName("mrp")
        @Expose
        private String mrp;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getItmName() {
            return itmName;
        }

        public void setItmName(String itmName) {
            this.itmName = itmName;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class CustJson {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("cus_name")
        @Expose
        private String cusName;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCusName() {
            return cusName;
        }

        public void setCusName(String cusName) {
            this.cusName = cusName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

    }


}
