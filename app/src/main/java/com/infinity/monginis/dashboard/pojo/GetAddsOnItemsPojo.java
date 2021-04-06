package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAddsOnItemsPojo {




    public class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("show_status")
        @Expose
        private Integer showStatus;
        @SerializedName("cat_name")
        @Expose
        private String catName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(Integer showStatus) {
            this.showStatus = showStatus;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

    }



    public class Example {

        @SerializedName("TOTAL")
        @Expose
        private Integer total;
        @SerializedName("MESSAGE")
        @Expose
        private String message;
        @SerializedName("RECORDS")
        @Expose
        private Records records;

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

        public Records getRecords() {
            return records;
        }

        public void setRecords(Records records) {
            this.records = records;
        }

    }



    public class Item {

        @SerializedName("itm_id")
        @Expose
        private Integer itmId;
        @SerializedName("sh_price")
        @Expose
        private Double shPrice;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("uom_id")
        @Expose
        private Integer uomId;
        @SerializedName("uom_name")
        @Expose
        private String uomName;
        @SerializedName("hsn_code")
        @Expose
        private String hsnCode;
        @SerializedName("qty")
        @Expose
        private Double qty;
        @SerializedName("mrp")
        @Expose
        private Double mrp;
        @SerializedName("cat_id")
        @Expose
        private Integer catId;
        @SerializedName("item_name")
        @Expose
        private String itemName;
        @SerializedName("itm_url")
        @Expose
        private String itmUrl;

        public Integer getItmId() {
            return itmId;
        }

        public void setItmId(Integer itmId) {
            this.itmId = itmId;
        }

        public Double getShPrice() {
            return shPrice;
        }

        public void setShPrice(Double shPrice) {
            this.shPrice = shPrice;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getUomId() {
            return uomId;
        }

        public void setUomId(Integer uomId) {
            this.uomId = uomId;
        }

        public String getUomName() {
            return uomName;
        }

        public void setUomName(String uomName) {
            this.uomName = uomName;
        }

        public String getHsnCode() {
            return hsnCode;
        }

        public void setHsnCode(String hsnCode) {
            this.hsnCode = hsnCode;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }

        public Double getMrp() {
            return mrp;
        }

        public void setMrp(Double mrp) {
            this.mrp = mrp;
        }

        public Integer getCatId() {
            return catId;
        }

        public void setCatId(Integer catId) {
            this.catId = catId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItmUrl() {
            return itmUrl;
        }

        public void setItmUrl(String itmUrl) {
            this.itmUrl = itmUrl;
        }

    }




    public class Records {

        @SerializedName("Category")
        @Expose
        private List<Category> category = null;
        @SerializedName("items")
        @Expose
        private List<Item> items = null;

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

    }
}
