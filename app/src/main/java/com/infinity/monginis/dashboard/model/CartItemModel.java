package com.infinity.monginis.dashboard.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

import java.io.Serializable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CartItemModel implements Serializable {


    private String itemId;
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
    private String selectedAddsOnArray;
    private String selectedItemArray;
    private String totalAddsOnPrice;
    private  MultipartBody.Part specialCakePhoto;


    public CartItemModel(String itemId,String itemName,String itemMrp,String menu,String weight,String flavours,String shape,String qty,String deliverydate,String yourMessage,String yourInstruction,String selectedAddsOnArray,String selectedItemArray,String menuID,String scheduleId,String totalAddsOnPrice,MultipartBody.Part specialCakePhoto){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemMrp = itemMrp;
        this.menu = menu;
        this.weight = weight;
        this.flavour =flavours;
        this.shape  = shape;
        this.qty = qty;
        this.deliverydate = deliverydate;
        this.yourMessage = yourMessage;
        this.yourInstruction = yourInstruction;
        this.selectedAddsOnArray = selectedAddsOnArray;
        this.selectedItemArray = selectedItemArray;
        this.menuId = menuID;
        this.scheduleId = scheduleId;
        this.totalAddsOnPrice = totalAddsOnPrice;
        this.specialCakePhoto = specialCakePhoto;
    }


    public CartItemModel(String id,String itemName, String itemMrp, String menu, String flavour, String shape, RequestBody photo, String deliverydate, String yourMessage, String yourInstruction, String weight,String menuId,String qty,String scheduleId) {
        this.itemName = itemName;
        this.itemMrp = itemMrp;
        this.itemId =id;
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

    public String getSelectedAddsOnArray() {
        return selectedAddsOnArray;
    }

    public void setSelectedAddsOnArray(String selectedAddsOnArray) {
        this.selectedAddsOnArray = selectedAddsOnArray;
    }

    public String getSelectedItemArray() {
        return selectedItemArray;
    }

    public void setSelectedItemArray(String selectedItemArray) {
        this.selectedItemArray = selectedItemArray;
    }

    public String getTotalAddsOnPrice() {
        return totalAddsOnPrice;
    }

    public void setTotalAddsOnPrice(String totalAddsOnPrice) {
        this.totalAddsOnPrice = totalAddsOnPrice;
    }

    public MultipartBody.Part getSpecialCakePhoto() {
        return specialCakePhoto;
    }

    public void setSpecialCakePhoto(MultipartBody.Part specialCakePhoto) {
        this.specialCakePhoto = specialCakePhoto;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
