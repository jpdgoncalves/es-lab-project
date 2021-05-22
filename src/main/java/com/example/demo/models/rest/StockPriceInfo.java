package com.example.demo.models.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockPriceInfo {
    private String updateDate;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;

}
