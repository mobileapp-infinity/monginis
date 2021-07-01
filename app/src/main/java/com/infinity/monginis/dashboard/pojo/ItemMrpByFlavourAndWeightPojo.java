package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemMrpByFlavourAndWeightPojo {


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

        @SerializedName("sgst_per")
        @Expose
        private Double sgstPer;
        @SerializedName("cgst_per")
        @Expose
        private Double cgstPer;
        @SerializedName("price")
        @Expose
        private Double price;

        public Double getSgstPer() {
            return sgstPer;
        }

        public void setSgstPer(Double sgstPer) {
            this.sgstPer = sgstPer;
        }

        public Double getCgstPer() {
            return cgstPer;
        }

        public void setCgstPer(Double cgstPer) {
            this.cgstPer = cgstPer;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

}


