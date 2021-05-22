package com.example.demo.models.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.SortedMap;
import java.util.TreeMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AVIntradayStocksInfo {

    @JsonProperty("Time Series (5min)")
    private SortedMap<String, AVStockPriceInfo> timeSeries = new TreeMap<>();

    public SortedMap<String, AVStockPriceInfo> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(SortedMap<String, AVStockPriceInfo> timeSeries) {
        this.timeSeries = timeSeries;
    }

    @Override
    public String toString() {
        return "IntradayStocksInfo{" +
                ", timeSeries=" + timeSeries +
                '}';
    }
}
