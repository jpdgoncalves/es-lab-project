package com.example.demo.services;

import com.example.demo.models.rest.AVIntradayStocksInfo;
import com.example.demo.models.rest.AVStockPriceInfo;
import com.example.demo.models.rest.IntradayStocksInfo;
import com.example.demo.models.rest.StockPriceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AVService {

    private RestTemplate restTemplate;
    private String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=FWCOU4ALBFAZY3CA";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public IntradayStocksInfo getStockInfo() {
        AVIntradayStocksInfo avIntradayStocksInfo = this.restTemplate.getForObject(url, AVIntradayStocksInfo.class);
        return AVIntradayStocksInfoToIntradayStockInfo(avIntradayStocksInfo);
    }

    public StockPriceInfo getLatestStockInfo() {
        IntradayStocksInfo stocksInfo = getStockInfo();
        List<StockPriceInfo> timeSeries = stocksInfo.getTimeSeries();

        return timeSeries.get(timeSeries.size() - 1);
    }

    private IntradayStocksInfo AVIntradayStocksInfoToIntradayStockInfo(AVIntradayStocksInfo stocksInfo) {
        IntradayStocksInfo intradayStocksInfo = new IntradayStocksInfo();
        List<StockPriceInfo> timeSeries = new ArrayList<>();


        for (Map.Entry<String, AVStockPriceInfo> entry : stocksInfo.getTimeSeries().entrySet()) {
            StockPriceInfo infoUpdate = AVStockPriceInfoToStockPriceInfoUpdate(entry.getKey(), entry.getValue());
            timeSeries.add(infoUpdate);
        }

        intradayStocksInfo.setTimeSeries(timeSeries);
        return intradayStocksInfo;
    }

    private StockPriceInfo AVStockPriceInfoToStockPriceInfoUpdate(String date, AVStockPriceInfo priceInfo) {
        StockPriceInfo infoUpdate = new StockPriceInfo();

        infoUpdate.setUpdateDate(date);
        infoUpdate.setOpen(priceInfo.getOpen());
        infoUpdate.setLow(priceInfo.getLow());
        infoUpdate.setHigh(priceInfo.getHigh());
        infoUpdate.setClose(priceInfo.getClose());
        infoUpdate.setVolume(priceInfo.getVolume());

        return infoUpdate;
    }
}
