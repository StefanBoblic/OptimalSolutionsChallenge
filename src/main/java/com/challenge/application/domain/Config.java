package com.challenge.application.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Config {
    @SerializedName("rows")
    private String rows;
    @SerializedName("columns")
    private int columns;
}
