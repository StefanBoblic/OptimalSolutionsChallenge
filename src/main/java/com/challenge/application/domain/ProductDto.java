package com.challenge.application.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public class ProductDto {
    @SerializedName("Product ID")
    private long productId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Amount")
    private int amount;
    @SerializedName("Price")
    private double price;

}
