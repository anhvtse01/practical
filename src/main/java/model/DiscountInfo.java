package model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DiscountInfo {
    @SerializedName("discountDate")
    private String discountDate;

    @SerializedName("discountPercent")
    private Double discountPercent;

    public DiscountInfo() {
    }

    public String getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        DiscountInfo that = (DiscountInfo) o;
        return Objects.equals(discountDate, that.discountDate) && Objects.equals(discountPercent, that.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountDate, discountPercent);
    }
}
