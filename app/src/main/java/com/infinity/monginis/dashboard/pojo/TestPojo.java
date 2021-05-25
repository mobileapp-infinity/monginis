package com.infinity.monginis.dashboard.pojo;

public class TestPojo {

    String shopName;
    String shopAddress;
    int is_special_flag;


    public TestPojo(String shopName, String shopAddress,int is_special_flag) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.is_special_flag = is_special_flag;
    }

    public TestPojo() {

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

    public int getIs_special_flag() {
        return is_special_flag;
    }

    public void setIs_special_flag(int is_special_flag) {
        this.is_special_flag = is_special_flag;
    }
}
