package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubSectionPojo {


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

        @SerializedName("icm_cat_name")
        @Expose
        private String icmCatName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("cat_type")
        @Expose
        private Integer catType;
        @SerializedName("icm_sys_file_name")
        @Expose
        private Object icmSysFileName;

        public String getIcmCatName() {
            return icmCatName;
        }

        public void setIcmCatName(String icmCatName) {
            this.icmCatName = icmCatName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCatType() {
            return catType;
        }

        public void setCatType(Integer catType) {
            this.catType = catType;
        }

        public Object getIcmSysFileName() {
            return icmSysFileName;
        }

        public void setIcmSysFileName(Object icmSysFileName) {
            this.icmSysFileName = icmSysFileName;
        }

    }

}



