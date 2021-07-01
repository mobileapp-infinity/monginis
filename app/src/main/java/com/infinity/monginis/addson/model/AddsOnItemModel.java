package com.infinity.monginis.addson.model;

public class AddsOnItemModel {

    private String itm_id;
    private String sh_price;
    private String price;
    private String uom_id;
    private String uom_name;
    private String hsn_code;
    private String qty;
    private String mrp;
    private String cat_id;
    private String item_name;
    private String itm_url;
    private String total_price;
    private String total_amount;


    public AddsOnItemModel(String itm_id, String sh_price, String price, String uom_id, String uom_name, String hsn_code, String qty, String mrp, String cat_id, String item_name, String itm_url,String total_price,String  total_amount) {
        this.itm_id = itm_id;
        this.sh_price = sh_price;
        this.price = price;
        this.uom_id = uom_id;
        this.uom_name = uom_name;
        this.hsn_code = hsn_code;
        this.qty = qty;
        this.mrp = mrp;
        this.cat_id = cat_id;
        this.item_name = item_name;
        this.itm_url = itm_url;
        this.total_price = total_price;
        this.total_amount = total_amount;
    }


    public String getItm_id() {
        return itm_id;
    }

    public void setItm_id(String itm_id) {
        this.itm_id = itm_id;
    }

    public String getSh_price() {
        return sh_price;
    }

    public void setSh_price(String sh_price) {
        this.sh_price = sh_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    public String getUom_name() {
        return uom_name;
    }

    public void setUom_name(String uom_name) {
        this.uom_name = uom_name;
    }

    public String getHsn_code() {
        return hsn_code;
    }

    public void setHsn_code(String hsn_code) {
        this.hsn_code = hsn_code;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItm_url() {
        return itm_url;
    }

    public void setItm_url(String itm_url) {
        this.itm_url = itm_url;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}
