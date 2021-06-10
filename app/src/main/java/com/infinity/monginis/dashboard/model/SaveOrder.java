package com.infinity.monginis.dashboard.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saveOrder")
public class SaveOrder {



   // private int itemId;
   @PrimaryKey(autoGenerate = true)
    private int itm_id;
    private String item_name;
    private String itm_hsn_code;
    private String itm_uom_id;
    private String itm_is_addon;
    private String act_price;
    private String itm_weight;
    private String cgst_per;
    private String sgst_per;
    private String sgst_tot;
    private String itm_total_amt;
    private String itm_net_amt;
    private String mrp;

    public SaveOrder( String item_name, String itm_hsn_code, String itm_uom_id, String itm_is_addon, String act_price, String itm_weight, String cgst_per, String sgst_per, String sgst_tot, String itm_total_amt, String itm_net_amt, String mrp) {
       // this.itm_id = itm_id;
        this.item_name = item_name;
        this.itm_hsn_code = itm_hsn_code;
        this.itm_uom_id = itm_uom_id;
        this.itm_is_addon = itm_is_addon;
        this.act_price = act_price;
        this.itm_weight = itm_weight;
        this.cgst_per = cgst_per;
        this.sgst_per = sgst_per;
        this.sgst_tot = sgst_tot;
        this.itm_total_amt = itm_total_amt;
        this.itm_net_amt = itm_net_amt;
        this.mrp = mrp;
    }


    public int getItm_id() {
        return itm_id;
    }

    public void setItm_id(int itm_id) {
        this.itm_id = itm_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItm_hsn_code() {
        return itm_hsn_code;
    }

    public void setItm_hsn_code(String itm_hsn_code) {
        this.itm_hsn_code = itm_hsn_code;
    }

    public String getItm_uom_id() {
        return itm_uom_id;
    }

    public void setItm_uom_id(String itm_uom_id) {
        this.itm_uom_id = itm_uom_id;
    }

    public String getItm_is_addon() {
        return itm_is_addon;
    }

    public void setItm_is_addon(String itm_is_addon) {
        this.itm_is_addon = itm_is_addon;
    }

    public String getAct_price() {
        return act_price;
    }

    public void setAct_price(String act_price) {
        this.act_price = act_price;
    }

    public String getItm_weight() {
        return itm_weight;
    }

    public void setItm_weight(String itm_weight) {
        this.itm_weight = itm_weight;
    }

    public String getCgst_per() {
        return cgst_per;
    }

    public void setCgst_per(String cgst_per) {
        this.cgst_per = cgst_per;
    }

    public String getSgst_per() {
        return sgst_per;
    }

    public void setSgst_per(String sgst_per) {
        this.sgst_per = sgst_per;
    }

    public String getSgst_tot() {
        return sgst_tot;
    }

    public void setSgst_tot(String sgst_tot) {
        this.sgst_tot = sgst_tot;
    }

    public String getItm_total_amt() {
        return itm_total_amt;
    }

    public void setItm_total_amt(String itm_total_amt) {
        this.itm_total_amt = itm_total_amt;
    }

    public String getItm_net_amt() {
        return itm_net_amt;
    }

    public void setItm_net_amt(String itm_net_amt) {
        this.itm_net_amt = itm_net_amt;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }
}
