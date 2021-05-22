package com.example.demo.services;

import com.example.demo.models.entities.StockPrice;
import com.example.demo.models.rest.StockPriceInfo;
import com.example.demo.repositories.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockPriceService {

    private StockPriceRepository repository;

    @Autowired
    public void setRepository(StockPriceRepository repository) {
        this.repository = repository;
    }

    public List<StockPriceInfo> getLatestPrices() {
        List<StockPriceInfo> stockPriceInfos = new ArrayList<>();
        List<StockPrice> stockPrices = repository.findTop100ByOrderByUpdateDateDesc();

        for (StockPrice stockPrice : stockPrices) {
            StockPriceInfo info = stockPriceToStockPriceInfo(stockPrice);
            stockPriceInfos.add(info);
        }

        return stockPriceInfos;
    }

    public void addEntry(StockPriceInfo info, String symbol) {
        StockPrice stockPrice = new StockPrice(
                symbol,
                info.getUpdateDate(),
                info.getOpen(),
                info.getHigh(),
                info.getLow(),
                info.getClose(),
                info.getVolume()
        );

        repository.save(stockPrice);
    }

    private StockPriceInfo stockPriceToStockPriceInfo(StockPrice stockPrice) {
        StockPriceInfo info = new StockPriceInfo();

        info.setUpdateDate(stockPrice.getUpdateDate());
        info.setOpen(stockPrice.getOpen());
        info.setHigh(stockPrice.getHigh());
        info.setLow(stockPrice.getLow());
        info.setClose(stockPrice.getClose());
        info.setVolume(stockPrice.getVolume());

        return info;
    }
}
