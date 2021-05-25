package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Get_Addons_Items_List_Pojo {
    @SerializedName("TOTAL")
    @Expose
    private Integer total;
    @SerializedName("MESSAGE")
    @Expose
    private String message;
    @SerializedName("RECORDS")
    @Expose
    private List<Item.Record> records = null;

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

    public List<Item.Record> getRecords() {
        return records;
    }

    public void setRecords(List<Item.Record> records) {
        this.records = records;
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
        @SerializedName("item_url")
        @Expose
        private String itemUrl;

        @SerializedName("totalAmt")
        @Expose
        private Double totalAmt;

        @SerializedName("total_net_amt")
        @Expose
        private Double total_net_amt;


        private String itemQty = "0";

        public String getItemQty() {
            return itemQty;
        }
        private String totalItems;

        public String getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(String totalItems) {
            this.totalItems = totalItems;
        }

        public void setItemQty(String itemQty) {
            this.itemQty = itemQty;
        }

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

        public String getItemUrl() {
            return itemUrl;
        }

        public void setItemUrl(String itemUrl) {
            this.itemUrl = itemUrl;
        }

        public Double getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(Double totalAmt) {
            this.totalAmt = totalAmt;
        }

        public Double getTotal_net_amt() {
            return total_net_amt;
        }

        public void setTotal_net_amt(Double total_net_amt) {
            this.total_net_amt = total_net_amt;
        }

        public class Record {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("show_status")
            @Expose
            private Integer showStatus;
            @SerializedName("cat_name")
            @Expose
            private String catName;
            @SerializedName("Items")
            @Expose
            private List<Item> items = null;

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

            public List<Item> getItems() {
                return items;
            }

            public void setItems(List<Item> items) {
                this.items = items;
            }


        }

    }

}








