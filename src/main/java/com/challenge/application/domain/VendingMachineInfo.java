package com.challenge.application.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class VendingMachineInfo {
    @SerializedName("config")
    private Config config;
    @SerializedName("items")
    private List<Product> product;

}
