package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOccasionPojo {
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
        @SerializedName("com_occasion_name")
        @Expose
        private String comOccasionName;

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

        public String getComOccasionName() {
            return comOccasionName;
        }

        public void setComOccasionName(String comOccasionName) {
            this.comOccasionName = comOccasionName;
        }
    }

}


