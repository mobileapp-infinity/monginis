package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemsForDashboardPojo {

    @SerializedName("TOTAL")
    @Expose
    private Integer total;
    @SerializedName("MESSAGE")
    @Expose
    private String message;
    @SerializedName("RECORDS")
    @Expose
    private List<Record> records = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public class Record {

        @SerializedName("itm_name")
        @Expose
        private String itmName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("itm_url")
        @Expose
        private Object itmUrl;
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
        private Integer isSpecialFlag;
        @SerializedName("cat_id")
        @Expose
        private Integer catId;
        @SerializedName("hsn_code")
        @Expose
        private String hsnCode;
        @SerializedName("itm_allow_trading")
        @Expose
        private Integer itmAllowTrading;
        @SerializedName("itm_uom")
        @Expose
        private Integer itmUom;
        @SerializedName("uom_name")
        @Expose
        private String uomName;

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

        public Object getItmUrl() {
            return itmUrl;
        }

        public void setItmUrl(Object itmUrl) {
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

        public Integer getIsSpecialFlag() {
            return isSpecialFlag;
        }

        public void setIsSpecialFlag(Integer isSpecialFlag) {
            this.isSpecialFlag = isSpecialFlag;
        }

        public Integer getCatId() {
            return catId;
        }

        public void setCatId(Integer catId) {
            this.catId = catId;
        }

        public String getHsnCode() {
            return hsnCode;
        }

        public void setHsnCode(String hsnCode) {
            this.hsnCode = hsnCode;
        }

        public Integer getItmAllowTrading() {
            return itmAllowTrading;
        }

        public void setItmAllowTrading(Integer itmAllowTrading) {
            this.itmAllowTrading = itmAllowTrading;
        }

        public Integer getItmUom() {
            return itmUom;
        }

        public void setItmUom(Integer itmUom) {
            this.itmUom = itmUom;
        }

        public String getUomName() {
            return uomName;
        }

        public void setUomName(String uomName) {
            this.uomName = uomName;
        }

    }
}






