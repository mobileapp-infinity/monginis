package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSectionPojo {
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

        @SerializedName("create_by_user")
        @Expose
        private String createByUser;
        @SerializedName("modify_by_user")
        @Expose
        private String modifyByUser;
        @SerializedName("create_dnt")
        @Expose
        private String createDnt;
        @SerializedName("csm_cat_ids")
        @Expose
        private String csmCatIds;
        @SerializedName("csm_originale_file_name")
        @Expose
        private String csmOriginaleFileName;
        @SerializedName("csm_section_name")
        @Expose
        private String csmSectionName;
        @SerializedName("csm_shop_id")
        @Expose
        private Integer csmShopId;
        @SerializedName("csm_system_file_name")
        @Expose
        private String csmSystemFileName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("modify_dnt")
        @Expose
        private String modifyDnt;
        @SerializedName("status")
        @Expose
        private Integer status;

        public String getCreateByUser() {
            return createByUser;
        }

        public void setCreateByUser(String createByUser) {
            this.createByUser = createByUser;
        }

        public String getModifyByUser() {
            return modifyByUser;
        }

        public void setModifyByUser(String modifyByUser) {
            this.modifyByUser = modifyByUser;
        }

        public String getCreateDnt() {
            return createDnt;
        }

        public void setCreateDnt(String createDnt) {
            this.createDnt = createDnt;
        }

        public String getCsmCatIds() {
            return csmCatIds;
        }

        public void setCsmCatIds(String csmCatIds) {
            this.csmCatIds = csmCatIds;
        }

        public String getCsmOriginaleFileName() {
            return csmOriginaleFileName;
        }

        public void setCsmOriginaleFileName(String csmOriginaleFileName) {
            this.csmOriginaleFileName = csmOriginaleFileName;
        }

        public String getCsmSectionName() {
            return csmSectionName;
        }

        public void setCsmSectionName(String csmSectionName) {
            this.csmSectionName = csmSectionName;
        }

        public Integer getCsmShopId() {
            return csmShopId;
        }

        public void setCsmShopId(Integer csmShopId) {
            this.csmShopId = csmShopId;
        }

        public String getCsmSystemFileName() {
            return csmSystemFileName;
        }

        public void setCsmSystemFileName(String csmSystemFileName) {
            this.csmSystemFileName = csmSystemFileName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getModifyDnt() {
            return modifyDnt;
        }

        public void setModifyDnt(String modifyDnt) {
            this.modifyDnt = modifyDnt;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }



    }

}




