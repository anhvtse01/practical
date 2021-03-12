package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

public class DiscountInfo {
    @SerializedName("discountDate")
    private String discountDate;

    @SerializedName("discountPrice")
    private Double discountPrice;

    public DiscountInfo() {
    }

    public String getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        DiscountInfo that = (DiscountInfo) o;
        return Objects.equals(discountDate, that.discountDate) && Objects.equals(discountPrice, that.discountPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountDate, discountPrice);
    }
}
