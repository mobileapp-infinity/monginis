package com.infinity.monginis.CategoryItemsDetails.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemDetailsByCategoryPojo {

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

        @SerializedName("itm_id")
        @Expose
        private Integer itmId;
        @SerializedName("itm_name")
        @Expose
        private String itmName;
        @SerializedName("itm_photo")
        @Expose
        private Object itmPhoto;
        @SerializedName("ItmUrl")
        @Expose
        private Object itmUrl;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("MRP")
        @Expose
        private Double mRP;
        @SerializedName("min_weight")
        @Expose
        private Double minWeight;
        @SerializedName("max_weight")
        @Expose
        private Double maxWeight;
        @SerializedName("flavours")
        @Expose
        private Object flavours;
        @SerializedName("shape")
        @Expose
        private Object shape;
        @SerializedName("itm_url2")
        @Expose
        private Object itmUrl2;


        private boolean isLiked;

        public boolean isLiked() {
            return isLiked;
        }

        public void setLiked(boolean liked) {
            isLiked = liked;
        }

        public Integer getItmId() {
            return itmId;
        }

        public void setItmId(Integer itmId) {
            this.itmId = itmId;
        }

        public String getItmName() {
            return itmName;
        }

        public void setItmName(String itmName) {
            this.itmName = itmName;
        }

        public Object getItmPhoto() {
            return itmPhoto;
        }

        public void setItmPhoto(Object itmPhoto) {
            this.itmPhoto = itmPhoto;
        }

        public Object getItmUrl() {
            return itmUrl;
        }

        public void setItmUrl(Object itmUrl) {
            this.itmUrl = itmUrl;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getMRP() {
            return mRP;
        }

        public void setMRP(Double mRP) {
            this.mRP = mRP;
        }

        public Double getMinWeight() {
            return minWeight;
        }

        public void setMinWeight(Double minWeight) {
            this.minWeight = minWeight;
        }

        public Double getMaxWeight() {
            return maxWeight;
        }

        public void setMaxWeight(Double maxWeight) {
            this.maxWeight = maxWeight;
        }

        public Object getFlavours() {
            return flavours;
        }

        public void setFlavours(Object flavours) {
            this.flavours = flavours;
        }

        public Object getShape() {
            return shape;
        }

        public void setShape(Object shape) {
            this.shape = shape;
        }

        public Object getItmUrl2() {
            return itmUrl2;
        }

        public void setItmUrl2(Object itmUrl2) {
            this.itmUrl2 = itmUrl2;
        }

    }

}



