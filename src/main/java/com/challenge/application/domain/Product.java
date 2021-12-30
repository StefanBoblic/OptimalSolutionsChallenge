package com.challenge.application.domain;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private int amount;
    @SerializedName("price")
    private double price;
}

