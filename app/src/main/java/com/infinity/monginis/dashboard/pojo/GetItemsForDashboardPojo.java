package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemsForDashboardPojo {

    @SerializedName("TOTAL")
    @Expose
    private Integer tOTAL;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("RECORDS")
    @Expose
    private List<RECORD> rECORDS = null;

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

    public List<RECORD> getRECORDS() {
        return rECORDS;
    }

    public void setRECORDS(List<RECORD> rECORDS) {
        this.rECORDS = rECORDS;
    }

    public class RECORD {

        @SerializedName("itm_name")
        @Expose
        private String itmName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("itm_url")
        @Expose
        private String itmUrl;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("itm_flv")
        @Expose
        private String itmFlv;
        @SerializedName("itm_shape")
        @Expose
        private String itmShape;

        @SerializedName("is_special_flag")
        @Expose
        private int is_special_flag;


        public String getItmName() {
            return itmName;
        }

        public void setItmName(String itmName) {
            this.itmName = itmName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getItmUrl() {
            return itmUrl;
        }

        public void setItmUrl(String itmUrl) {
            this.itmUrl = itmUrl;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getItmFlv() {
            return itmFlv;
        }

        public void setItmFlv(String itmFlv) {
            this.itmFlv = itmFlv;
        }

        public String getItmShape() {
            return itmShape;
        }

        public void setItmShape(String itmShape) {
            this.itmShape = itmShape;
        }

        public int getIs_special_flag() {
            return is_special_flag;
        }

        public void setIs_special_flag(int is_special_flag) {
            this.is_special_flag = is_special_flag;
        }
    }

}



