package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemsPhotoForDashboardAppPojo {

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

        @SerializedName("itm_url")
        @Expose
        private String itmUrl;

        public String getItmUrl() {
            return itmUrl;
        }

        public void setItmUrl(String itmUrl) {
            this.itmUrl = itmUrl;
        }

    }
}



