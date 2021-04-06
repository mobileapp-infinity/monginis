package com.infinity.monginis.ShopForItemActiivty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetShopListForItemStockPojo {
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

        @SerializedName("CLOSING")
        @Expose
        private Double cLOSING;
        @SerializedName("shop_id")
        @Expose
        private Integer shopId;
        @SerializedName("cus_name")
        @Expose
        private String cusName;
        @SerializedName("cus_mobile_no")
        @Expose
        private Object cusMobileNo;
        @SerializedName("cus_add1")
        @Expose
        private String cusAdd1;
        @SerializedName("cus_add2")
        @Expose
        private Object cusAdd2;
        @SerializedName("cus_add3")
        @Expose
        private Object cusAdd3;

        private int viewType;

        public Double getCLOSING() {
            return cLOSING;
        }

        public void setCLOSING(Double cLOSING) {
            this.cLOSING = cLOSING;
        }

        public Integer getShopId() {
            return shopId;
        }

        public void setShopId(Integer shopId) {
            this.shopId = shopId;
        }

        public String getCusName() {
            return cusName;
        }

        public void setCusName(String cusName) {
            this.cusName = cusName;
        }

        public Object getCusMobileNo() {
            return cusMobileNo;
        }

        public void setCusMobileNo(Object cusMobileNo) {
            this.cusMobileNo = cusMobileNo;
        }

        public String getCusAdd1() {
            return cusAdd1;
        }

        public void setCusAdd1(String cusAdd1) {
            this.cusAdd1 = cusAdd1;
        }

        public Object getCusAdd2() {
            return cusAdd2;
        }

        public void setCusAdd2(Object cusAdd2) {
            this.cusAdd2 = cusAdd2;
        }

        public Object getCusAdd3() {
            return cusAdd3;
        }

        public void setCusAdd3(Object cusAdd3) {
            this.cusAdd3 = cusAdd3;
        }
    }

}




