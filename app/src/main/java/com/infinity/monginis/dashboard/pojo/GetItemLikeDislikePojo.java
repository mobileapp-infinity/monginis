package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemLikeDislikePojo {


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
        private Object modifyBy;
        @SerializedName("modify_ip")
        @Expose
        private Object modifyIp;
        @SerializedName("modify_dnt")
        @Expose
        private Object modifyDnt;
        @SerializedName("rt_item_id")
        @Expose
        private Integer rtItemId;
        @SerializedName("rt_mobile_no")
        @Expose
        private String rtMobileNo;

        //like_dislike_flag

        @SerializedName("like_dislike_flag")
        @Expose
        private String like_dislike_flag;

        //itm_name

        @SerializedName("itm_name")
        @Expose
        private String itm_name;

        //itm_mrp

        @SerializedName("itm_mrp")
        @Expose
        private String itm_mrp;




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

        public Integer getRtItemId() {
            return rtItemId;
        }

        public void setRtItemId(Integer rtItemId) {
            this.rtItemId = rtItemId;
        }

        public String getRtMobileNo() {
            return rtMobileNo;
        }

        public void setRtMobileNo(String rtMobileNo) {
            this.rtMobileNo = rtMobileNo;
        }

        public String getLike_dislike_flag() {
            return like_dislike_flag;
        }

        public void setLike_dislike_flag(String like_dislike_flag) {
            this.like_dislike_flag = like_dislike_flag;


        }

        public String getItm_name() {
            return itm_name;
        }

        public void setItm_name(String itm_name) {
            this.itm_name = itm_name;
        }

        public String getItm_mrp() {
            return itm_mrp;
        }

        public void setItm_mrp(String itm_mrp) {
            this.itm_mrp = itm_mrp;
        }
    }


}


