package com.infinity.monginis.dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfrimOrderReponsePojo {

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

    public class Records {

        @SerializedName("main")
        @Expose
        private List<Main> main = null;
        @SerializedName("addons")
        @Expose
        private List<Addon> addons = null;
        @SerializedName("accesories")
        @Expose
        private List<Accesory> accesories = null;

        public List<Main> getMain() {
            return main;
        }

        public void setMain(List<Main> main) {
            this.main = main;
        }

        public List<Addon> getAddons() {
            return addons;
        }

        public void setAddons(List<Addon> addons) {
            this.addons = addons;
        }

        public List<Accesory> getAccesories() {
            return accesories;
        }

        public void setAccesories(List<Accesory> accesories) {
            this.accesories = accesories;
        }

        public class Main {

            @SerializedName("item_id")
            @Expose
            private Integer itemId;
            @SerializedName("sroid_itm_is_addon")
            @Expose
            private Integer sroidItmIsAddon;
            @SerializedName("sroid_itm_qty")
            @Expose
            private Integer sroidItmQty;
            @SerializedName("sroid_itm_weight")
            @Expose
            private Double sroidItmWeight;
            @SerializedName("sroid_itm_uom")
            @Expose
            private String sroidItmUom;
            @SerializedName("item_net_amt")
            @Expose
            private Integer itemNetAmt;
            @SerializedName("mrp")
            @Expose
            private Integer mrp;
            @SerializedName("net_mrp_amt")
            @Expose
            private Double netMrpAmt;
            @SerializedName("itm_name")
            @Expose
            private String itmName;
            @SerializedName("itm_price")
            @Expose
            private Integer itmPrice;
            @SerializedName("itm")
            @Expose
            private String itm;
            @SerializedName("del_date")
            @Expose
            private String delDate;
            @SerializedName("url_img")
            @Expose
            private String urlImg;
            @SerializedName("srom_message")
            @Expose
            private String sromMessage;
            @SerializedName("sroid_cgst_per")
            @Expose
            private Integer sroidCgstPer;
            @SerializedName("sroid_sgst_per")
            @Expose
            private Integer sroidSgstPer;
            @SerializedName("sroid_igst_per")
            @Expose
            private Integer sroidIgstPer;
            @SerializedName("sroid_cgst_total_amt")
            @Expose
            private Integer sroidCgstTotalAmt;
            @SerializedName("sroid_sgst_total_amt")
            @Expose
            private Integer sroidSgstTotalAmt;
            @SerializedName("sroid_igst_total_amt")
            @Expose
            private Integer sroidIgstTotalAmt;
            @SerializedName("srom_schedule_id")
            @Expose
            private Integer sromScheduleId;
            @SerializedName("srom_shop_id")
            @Expose
            private Integer sromShopId;
            @SerializedName("srom_spec_instruction")
            @Expose
            private String sromSpecInstruction;
            @SerializedName("srom_occasion")
            @Expose
            private Integer sromOccasion;
            @SerializedName("flavour")
            @Expose
            private String flavour;
            @SerializedName("shape")
            @Expose
            private Object shape;

            public Integer getItemId() {
                return itemId;
            }

            public void setItemId(Integer itemId) {
                this.itemId = itemId;
            }

            public Integer getSroidItmIsAddon() {
                return sroidItmIsAddon;
            }

            public void setSroidItmIsAddon(Integer sroidItmIsAddon) {
                this.sroidItmIsAddon = sroidItmIsAddon;
            }

            public Integer getSroidItmQty() {
                return sroidItmQty;
            }

            public void setSroidItmQty(Integer sroidItmQty) {
                this.sroidItmQty = sroidItmQty;
            }

            public Double getSroidItmWeight() {
                return sroidItmWeight;
            }

            public void setSroidItmWeight(Double sroidItmWeight) {
                this.sroidItmWeight = sroidItmWeight;
            }

            public String getSroidItmUom() {
                return sroidItmUom;
            }

            public void setSroidItmUom(String sroidItmUom) {
                this.sroidItmUom = sroidItmUom;
            }

            public Integer getItemNetAmt() {
                return itemNetAmt;
            }

            public void setItemNetAmt(Integer itemNetAmt) {
                this.itemNetAmt = itemNetAmt;
            }

            public Integer getMrp() {
                return mrp;
            }

            public void setMrp(Integer mrp) {
                this.mrp = mrp;
            }

            public Double getNetMrpAmt() {
                return netMrpAmt;
            }

            public void setNetMrpAmt(Double netMrpAmt) {
                this.netMrpAmt = netMrpAmt;
            }

            public String getItmName() {
                return itmName;
            }

            public void setItmName(String itmName) {
                this.itmName = itmName;
            }

            public Integer getItmPrice() {
                return itmPrice;
            }

            public void setItmPrice(Integer itmPrice) {
                this.itmPrice = itmPrice;
            }

            public String getItm() {
                return itm;
            }

            public void setItm(String itm) {
                this.itm = itm;
            }

            public String getDelDate() {
                return delDate;
            }

            public void setDelDate(String delDate) {
                this.delDate = delDate;
            }

            public String getUrlImg() {
                return urlImg;
            }

            public void setUrlImg(String urlImg) {
                this.urlImg = urlImg;
            }

            public String getSromMessage() {
                return sromMessage;
            }

            public void setSromMessage(String sromMessage) {
                this.sromMessage = sromMessage;
            }

            public Integer getSroidCgstPer() {
                return sroidCgstPer;
            }

            public void setSroidCgstPer(Integer sroidCgstPer) {
                this.sroidCgstPer = sroidCgstPer;
            }

            public Integer getSroidSgstPer() {
                return sroidSgstPer;
            }

            public void setSroidSgstPer(Integer sroidSgstPer) {
                this.sroidSgstPer = sroidSgstPer;
            }

            public Integer getSroidIgstPer() {
                return sroidIgstPer;
            }

            public void setSroidIgstPer(Integer sroidIgstPer) {
                this.sroidIgstPer = sroidIgstPer;
            }

            public Integer getSroidCgstTotalAmt() {
                return sroidCgstTotalAmt;
            }

            public void setSroidCgstTotalAmt(Integer sroidCgstTotalAmt) {
                this.sroidCgstTotalAmt = sroidCgstTotalAmt;
            }

            public Integer getSroidSgstTotalAmt() {
                return sroidSgstTotalAmt;
            }

            public void setSroidSgstTotalAmt(Integer sroidSgstTotalAmt) {
                this.sroidSgstTotalAmt = sroidSgstTotalAmt;
            }

            public Integer getSroidIgstTotalAmt() {
                return sroidIgstTotalAmt;
            }

            public void setSroidIgstTotalAmt(Integer sroidIgstTotalAmt) {
                this.sroidIgstTotalAmt = sroidIgstTotalAmt;
            }

            public Integer getSromScheduleId() {
                return sromScheduleId;
            }

            public void setSromScheduleId(Integer sromScheduleId) {
                this.sromScheduleId = sromScheduleId;
            }

            public Integer getSromShopId() {
                return sromShopId;
            }

            public void setSromShopId(Integer sromShopId) {
                this.sromShopId = sromShopId;
            }

            public String getSromSpecInstruction() {
                return sromSpecInstruction;
            }

            public void setSromSpecInstruction(String sromSpecInstruction) {
                this.sromSpecInstruction = sromSpecInstruction;
            }

            public Integer getSromOccasion() {
                return sromOccasion;
            }

            public void setSromOccasion(Integer sromOccasion) {
                this.sromOccasion = sromOccasion;
            }

            public String getFlavour() {
                return flavour;
            }

            public void setFlavour(String flavour) {
                this.flavour = flavour;
            }

            public Object getShape() {
                return shape;
            }

            public void setShape(Object shape) {
                this.shape = shape;
            }

        }

        public class Accesory {

            @SerializedName("LinkItem")
            @Expose
            private String linkItem;
            @SerializedName("Quantity")
            @Expose
            private Integer quantity;

            public String getLinkItem() {
                return linkItem;
            }

            public void setLinkItem(String linkItem) {
                this.linkItem = linkItem;
            }

            public Integer getQuantity() {
                return quantity;
            }

            public void setQuantity(Integer quantity) {
                this.quantity = quantity;
            }

        }

        public class Addon {

            @SerializedName("item_id")
            @Expose
            private Integer itemId;
            @SerializedName("sroid_itm_is_addon")
            @Expose
            private Integer sroidItmIsAddon;
            @SerializedName("sroid_itm_qty")
            @Expose
            private Integer sroidItmQty;
            @SerializedName("sroid_itm_weight")
            @Expose
            private Integer sroidItmWeight;
            @SerializedName("sroid_itm_uom")
            @Expose
            private String sroidItmUom;
            @SerializedName("item_net_amt")
            @Expose
            private Integer itemNetAmt;
            @SerializedName("mrp")
            @Expose
            private Integer mrp;
            @SerializedName("net_mrp_amt")
            @Expose
            private Integer netMrpAmt;
            @SerializedName("itm_name")
            @Expose
            private String itmName;
            @SerializedName("itm_price")
            @Expose
            private Integer itmPrice;
            @SerializedName("itm")
            @Expose
            private String itm;
            @SerializedName("del_date")
            @Expose
            private String delDate;
            @SerializedName("url_img")
            @Expose
            private String urlImg;
            @SerializedName("srom_message")
            @Expose
            private String sromMessage;
            @SerializedName("sroid_cgst_per")
            @Expose
            private Integer sroidCgstPer;
            @SerializedName("sroid_sgst_per")
            @Expose
            private Integer sroidSgstPer;
            @SerializedName("sroid_igst_per")
            @Expose
            private Integer sroidIgstPer;
            @SerializedName("sroid_cgst_total_amt")
            @Expose
            private Integer sroidCgstTotalAmt;
            @SerializedName("sroid_sgst_total_amt")
            @Expose
            private Integer sroidSgstTotalAmt;
            @SerializedName("sroid_igst_total_amt")
            @Expose
            private Integer sroidIgstTotalAmt;
            @SerializedName("srom_schedule_id")
            @Expose
            private Integer sromScheduleId;
            @SerializedName("srom_shop_id")
            @Expose
            private Integer sromShopId;
            @SerializedName("srom_spec_instruction")
            @Expose
            private String sromSpecInstruction;
            @SerializedName("srom_occasion")
            @Expose
            private Integer sromOccasion;
            @SerializedName("flavour")
            @Expose
            private String flavour;
            @SerializedName("shape")
            @Expose
            private Object shape;

            public Integer getItemId() {
                return itemId;
            }

            public void setItemId(Integer itemId) {
                this.itemId = itemId;
            }

            public Integer getSroidItmIsAddon() {
                return sroidItmIsAddon;
            }

            public void setSroidItmIsAddon(Integer sroidItmIsAddon) {
                this.sroidItmIsAddon = sroidItmIsAddon;
            }

            public Integer getSroidItmQty() {
                return sroidItmQty;
            }

            public void setSroidItmQty(Integer sroidItmQty) {
                this.sroidItmQty = sroidItmQty;
            }

            public Integer getSroidItmWeight() {
                return sroidItmWeight;
            }

            public void setSroidItmWeight(Integer sroidItmWeight) {
                this.sroidItmWeight = sroidItmWeight;
            }

            public String getSroidItmUom() {
                return sroidItmUom;
            }

            public void setSroidItmUom(String sroidItmUom) {
                this.sroidItmUom = sroidItmUom;
            }

            public Integer getItemNetAmt() {
                return itemNetAmt;
            }

            public void setItemNetAmt(Integer itemNetAmt) {
                this.itemNetAmt = itemNetAmt;
            }

            public Integer getMrp() {
                return mrp;
            }

            public void setMrp(Integer mrp) {
                this.mrp = mrp;
            }

            public Integer getNetMrpAmt() {
                return netMrpAmt;
            }

            public void setNetMrpAmt(Integer netMrpAmt) {
                this.netMrpAmt = netMrpAmt;
            }

            public String getItmName() {
                return itmName;
            }

            public void setItmName(String itmName) {
                this.itmName = itmName;
            }

            public Integer getItmPrice() {
                return itmPrice;
            }

            public void setItmPrice(Integer itmPrice) {
                this.itmPrice = itmPrice;
            }

            public String getItm() {
                return itm;
            }

            public void setItm(String itm) {
                this.itm = itm;
            }

            public String getDelDate() {
                return delDate;
            }

            public void setDelDate(String delDate) {
                this.delDate = delDate;
            }

            public String getUrlImg() {
                return urlImg;
            }

            public void setUrlImg(String urlImg) {
                this.urlImg = urlImg;
            }

            public String getSromMessage() {
                return sromMessage;
            }

            public void setSromMessage(String sromMessage) {
                this.sromMessage = sromMessage;
            }

            public Integer getSroidCgstPer() {
                return sroidCgstPer;
            }

            public void setSroidCgstPer(Integer sroidCgstPer) {
                this.sroidCgstPer = sroidCgstPer;
            }

            public Integer getSroidSgstPer() {
                return sroidSgstPer;
            }

            public void setSroidSgstPer(Integer sroidSgstPer) {
                this.sroidSgstPer = sroidSgstPer;
            }

            public Integer getSroidIgstPer() {
                return sroidIgstPer;
            }

            public void setSroidIgstPer(Integer sroidIgstPer) {
                this.sroidIgstPer = sroidIgstPer;
            }

            public Integer getSroidCgstTotalAmt() {
                return sroidCgstTotalAmt;
            }

            public void setSroidCgstTotalAmt(Integer sroidCgstTotalAmt) {
                this.sroidCgstTotalAmt = sroidCgstTotalAmt;
            }

            public Integer getSroidSgstTotalAmt() {
                return sroidSgstTotalAmt;
            }

            public void setSroidSgstTotalAmt(Integer sroidSgstTotalAmt) {
                this.sroidSgstTotalAmt = sroidSgstTotalAmt;
            }

            public Integer getSroidIgstTotalAmt() {
                return sroidIgstTotalAmt;
            }

            public void setSroidIgstTotalAmt(Integer sroidIgstTotalAmt) {
                this.sroidIgstTotalAmt = sroidIgstTotalAmt;
            }

            public Integer getSromScheduleId() {
                return sromScheduleId;
            }

            public void setSromScheduleId(Integer sromScheduleId) {
                this.sromScheduleId = sromScheduleId;
            }

            public Integer getSromShopId() {
                return sromShopId;
            }

            public void setSromShopId(Integer sromShopId) {
                this.sromShopId = sromShopId;
            }

            public String getSromSpecInstruction() {
                return sromSpecInstruction;
            }

            public void setSromSpecInstruction(String sromSpecInstruction) {
                this.sromSpecInstruction = sromSpecInstruction;
            }

            public Integer getSromOccasion() {
                return sromOccasion;
            }

            public void setSromOccasion(Integer sromOccasion) {
                this.sromOccasion = sromOccasion;
            }

            public String getFlavour() {
                return flavour;
            }

            public void setFlavour(String flavour) {
                this.flavour = flavour;
            }

            public Object getShape() {
                return shape;
            }

            public void setShape(Object shape) {
                this.shape = shape;
            }

        }

    }
}
