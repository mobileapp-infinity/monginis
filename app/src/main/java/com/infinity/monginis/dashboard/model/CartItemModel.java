package com.infinity.monginis.dashboard.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import okhttp3.RequestBody;

public class CartItemModel implements Serializable {


    private String shopName;
    private String shopAddress;
    private String itemImage;
    private String itemName;
    private String itemMrp;
    private String menu;
    private String menuId;
    private String flavour;
    private String shape;
    private RequestBody photo;
    private String deliverydate;
    private String yourMessage;
    private String yourInstruction;
    private String weight;
    private String qty;
    private String scheduleId;


    public CartItemModel(String itemName, String itemMrp, String menu, String flavour, String shape, RequestBody photo, String deliverydate, String yourMessage, String yourInstruction, String weight,String menuId,String qty,String scheduleId) {
        this.itemName = itemName;
        this.itemMrp = itemMrp;

        this.menu = menu;

        this.flavour = flavour;
        this.shape = shape;
        this.photo = photo;
        this.deliverydate = deliverydate;
        this.yourMessage = yourMessage;
        this.yourInstruction = yourInstruction;
        this.weight = weight;
        this.menuId = menuId;
        this.qty =qty;
        this.scheduleId =scheduleId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMrp() {
        return itemMrp;
    }

    public void setItemMrp(String itemMrp) {
        this.itemMrp = itemMrp;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public RequestBody getPhoto() {
        return photo;
    }

    public void setPhoto(RequestBody photo) {
        this.photo = photo;
    }

    public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getYourMessage() {
        return yourMessage;
    }

    public void setYourMessage(String yourMessage) {
        this.yourMessage = yourMessage;
    }

    public String getYourInstruction() {
        return yourInstruction;
    }

    public void setYourInstruction(String yourInstruction) {
        this.yourInstruction = yourInstruction;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;


    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
}
