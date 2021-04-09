package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllShopPojo {


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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("cus_code")
        @Expose
        private String cusCode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("cus_name")
        @Expose
        private String cusName;
        @SerializedName("cus_sta_id")
        @Expose
        private Integer cusStaId;
        @SerializedName("cus_cit_id")
        @Expose
        private Integer cusCitId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCusCode() {
            return cusCode;
        }

        public void setCusCode(String cusCode) {
            this.cusCode = cusCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCusName() {
            return cusName;
        }

        public void setCusName(String cusName) {
            this.cusName = cusName;
        }

        public Integer getCusStaId() {
            return cusStaId;
        }

        public void setCusStaId(Integer cusStaId) {
            this.cusStaId = cusStaId;
        }

        public Integer getCusCitId() {
            return cusCitId;
        }

        public void setCusCitId(Integer cusCitId) {
            this.cusCitId = cusCitId;
        }
    }

}




