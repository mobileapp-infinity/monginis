package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemWeightPojo {

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

        @SerializedName("gr_id")
        @Expose
        private Integer grId;
        @SerializedName("qty_value")
        @Expose
        private String qtyValue;
        @SerializedName("qty_text")
        @Expose
        private String qtyText;

        public Integer getGrId() {
            return grId;
        }

        public void setGrId(Integer grId) {
            this.grId = grId;
        }

        public String getQtyValue() {
            return qtyValue;
        }

        public void setQtyValue(String qtyValue) {
            this.qtyValue = qtyValue;
        }

        public String getQtyText() {
            return qtyText;
        }

        public void setQtyText(String qtyText) {
            this.qtyText = qtyText;
        }


    }

}



