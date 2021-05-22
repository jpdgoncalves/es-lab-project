package com.example.demo.models.rest;

import java.util.Comparator;
import java.util.List;

public class IntradayStocksInfo {

    private List<StockPriceInfo> timeSeries;

    public List<StockPriceInfo> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(List<StockPriceInfo> timeSeries) {
        this.timeSeries = timeSeries;
        this.timeSeries.sort(Comparator.comparing(StockPriceInfo::getUpdateDate));
    }
}
