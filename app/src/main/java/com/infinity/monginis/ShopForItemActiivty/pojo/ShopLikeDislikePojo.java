package com.infinity.monginis.ShopForItemActiivty.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopLikeDislikePojo {

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
        private Integer modifyBy;
        @SerializedName("modify_ip")
        @Expose
        private String modifyIp;
        @SerializedName("modify_dnt")
        @Expose
        private String modifyDnt;
        @SerializedName("rt_shop_id")
        @Expose
        private Integer rtShopId;
        @SerializedName("rt_like_displike_flag")
        @Expose
        private Integer rtLikeDisplikeFlag;
        @SerializedName("rt_mobile_no")
        @Expose
        private String rtMobileNo;

        //cus_name

        @SerializedName("cus_name")
        @Expose
        private String cus_name;

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

        public Integer getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(Integer modifyBy) {
            this.modifyBy = modifyBy;
        }

        public String getModifyIp() {
            return modifyIp;
        }

        public void setModifyIp(String modifyIp) {
            this.modifyIp = modifyIp;
        }

        public String getModifyDnt() {
            return modifyDnt;
        }

        public void setModifyDnt(String modifyDnt) {
            this.modifyDnt = modifyDnt;
        }

        public Integer getRtShopId() {
            return rtShopId;
        }

        public void setRtShopId(Integer rtShopId) {
            this.rtShopId = rtShopId;
        }

        public Integer getRtLikeDisplikeFlag() {
            return rtLikeDisplikeFlag;
        }

        public void setRtLikeDisplikeFlag(Integer rtLikeDisplikeFlag) {
            this.rtLikeDisplikeFlag = rtLikeDisplikeFlag;
        }

        public String getRtMobileNo() {
            return rtMobileNo;
        }

        public void setRtMobileNo(String rtMobileNo) {
            this.rtMobileNo = rtMobileNo;
        }

        public String getCus_name() {
            return cus_name;
        }

        public void setCus_name(String cus_name) {
            this.cus_name = cus_name;
        }
    }

}


