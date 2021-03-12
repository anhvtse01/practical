package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Article {

    @SerializedName("name")
    private String name;

    @SerializedName("slogan")
    private String slogan;

    @SerializedName("netPrice")
    private Double netPrice;

    @SerializedName("vatRatio")
    private Double vatRatio;

    @SerializedName("discountInfo")
    private List<DiscountInfo> discountInfo = new ArrayList<>();

    public Article() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public Double getVatRatio() {
        return vatRatio;
    }

    public void setVatRatio(Double vatRatio) {
        this.vatRatio = vatRatio;
    }

    public List<DiscountInfo> getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(List<DiscountInfo> discountInfo) {
        this.discountInfo = discountInfo;
    }
}
