package com.solvd.bankapp.bin;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PayType {
    @JsonProperty("full time")
    FULL_TIME,
    @JsonProperty("part time")
    PART_TIME;
}
