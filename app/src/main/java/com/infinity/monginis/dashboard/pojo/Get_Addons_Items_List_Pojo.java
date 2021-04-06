package com.infinity.monginis.dashboard.pojo;

import java.util.List;

public class Get_Addons_Items_List_Pojo {


    /**
     * TOTAL : 7
     * MESSAGE : Record Found
     * RECORDS : {"Category":[{"id":34,"show_status":0,"cat_name":"Breads"},{"id":30,"show_status":0,"cat_name":"GAUTAX"},{"id":33,"show_status":0,"cat_name":"Special Cake"}],"items":[{"itm_id":56,"sh_price":23.6,"price":20,"uom_id":2,"uom_name":"NOS","hsn_code":"123456","qty":0,"mrp":40,"cat_id":30,"item_name":"SLICE BREAD & SUGAR"},{"itm_id":437,"sh_price":141.6,"price":120,"uom_id":2,"uom_name":"NOS","hsn_code":"123456","qty":0,"mrp":120,"cat_id":30,"item_name":"SLICE BREAD 2"},{"itm_id":496,"sh_price":59,"price":50,"uom_id":6,"uom_name":"PACK","hsn_code":"123456","qty":0,"mrp":70,"cat_id":34,"item_name":"LEMON GINGER"},{"itm_id":497,"sh_price":0,"price":0,"uom_id":1,"uom_name":"KG","hsn_code":"123456","qty":0,"mrp":0,"cat_id":33,"item_name":"BANANA & CHOC BUNDT"},{"itm_id":505,"sh_price":141.6,"price":120,"uom_id":20,"uom_name":"Bag","hsn_code":"123456","qty":0,"mrp":120,"cat_id":33,"item_name":"PEANUT"},{"itm_id":535,"sh_price":0,"price":0,"uom_id":31,"uom_name":"BOX","hsn_code":"123456","qty":0,"mrp":0,"cat_id":33,"item_name":"KISMIS"},{"itm_id":543,"sh_price":120,"price":120,"uom_id":2,"uom_name":"NOS","hsn_code":"","qty":0,"mrp":120,"cat_id":33,"item_name":"APPLE"}]}
     */

    private int TOTAL;
    private String MESSAGE;
    private RECORDSBean RECORDS;

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public RECORDSBean getRECORDS() {
        return RECORDS;
    }

    public void setRECORDS(RECORDSBean RECORDS) {
        this.RECORDS = RECORDS;
    }

    public static class RECORDSBean {
        private List<CategoryBean> Category;
        private List<ItemsBean> items;

        public List<CategoryBean> getCategory() {
            return Category;
        }

        public void setCategory(List<CategoryBean> Category) {
            this.Category = Category;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class CategoryBean {
            /**
             * id : 34
             * show_status : 0
             * cat_name : Breads
             */

            private int id;
            private int show_status;
            private String cat_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShow_status() {
                return show_status;
            }

            public void setShow_status(int show_status) {
                this.show_status = show_status;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }
        }

        public static class ItemsBean {
            /**
             * itm_id : 56
             * sh_price : 23.6
             * price : 20
             * uom_id : 2
             * uom_name : NOS
             * hsn_code : 123456
             * qty : 0
             * mrp : 40
             * cat_id : 30
             * item_name : SLICE BREAD & SUGAR
             */

            private int itm_id;
            private double sh_price;
            private double price;
            private int uom_id;
            private String uom_name;
            private String hsn_code;
            private int qty;
            private int mrp;
            private int cat_id;
            private String item_name;
            private String itm_url;

            public int getItm_id() {
                return itm_id;
            }

            public void setItm_id(int itm_id) {
                this.itm_id = itm_id;
            }

            public double getSh_price() {
                return sh_price;
            }

            public void setSh_price(double sh_price) {
                this.sh_price = sh_price;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getUom_id() {
                return uom_id;
            }

            public void setUom_id(int uom_id) {
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

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public int getMrp() {
                return mrp;
            }

            public void setMrp(int mrp) {
                this.mrp = mrp;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
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
        }
    }
}
