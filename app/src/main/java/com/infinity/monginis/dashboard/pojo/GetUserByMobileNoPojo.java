package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetUserByMobileNoPojo  implements Serializable {

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

    public class Record implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("comp_id")
        @Expose
        private Integer compId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("create_by")
        @Expose
        private Integer createBy;
        @SerializedName("create_ip")
        @Expose
        private String createIp;
        @SerializedName("create_dnt")
        @Expose
        private String createDnt;
        @SerializedName("modify_by")
        @Expose
        private Object modifyBy;
        @SerializedName("modify_ip")
        @Expose
        private Object modifyIp;
        @SerializedName("modify_dnt")
        @Expose
        private Object modifyDnt;
        @SerializedName("ud_address")
        @Expose
        private String udAddress;
        @SerializedName("ud_order_id")
        @Expose
        private Integer udOrderId;
        @SerializedName("ud_mobile_no")
        @Expose
        private String udMobileNo;


        private boolean isSelected = false;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCompId() {
            return compId;
        }

        public void setCompId(Integer compId) {
            this.compId = compId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Integer createBy) {
            this.createBy = createBy;
        }

        public String getCreateIp() {
            return createIp;
        }

        public void setCreateIp(String createIp) {
            this.createIp = createIp;
        }

        public String getCreateDnt() {
            return createDnt;
        }

        public void setCreateDnt(String createDnt) {
            this.createDnt = createDnt;
        }

        public Object getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Object modifyBy) {
            this.modifyBy = modifyBy;
        }

        public Object getModifyIp() {
            return modifyIp;
        }

        public void setModifyIp(Object modifyIp) {
            this.modifyIp = modifyIp;
        }

        public Object getModifyDnt() {
            return modifyDnt;
        }

        public void setModifyDnt(Object modifyDnt) {
            this.modifyDnt = modifyDnt;
        }

        public String getUdAddress() {
            return udAddress;
        }

        public void setUdAddress(String udAddress) {
            this.udAddress = udAddress;
        }

        public Integer getUdOrderId() {
            return udOrderId;
        }

        public void setUdOrderId(Integer udOrderId) {
            this.udOrderId = udOrderId;
        }

        public String getUdMobileNo() {
            return udMobileNo;
        }

        public void setUdMobileNo(String udMobileNo) {
            this.udMobileNo = udMobileNo;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}



