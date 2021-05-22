package com.example.demo.schedulers;

import com.example.demo.models.rest.IntradayStocksInfo;
import com.example.demo.services.AVService;
import com.example.demo.services.StockPriceService;
import com.example.demo.websockets.WebSocketHandler;
import com.example.demo.models.rest.StockPriceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AVScheduler {

    private static final Logger logger= LoggerFactory.getLogger(AVScheduler.class);
    private String lastUpdate = null;

    private WebSocketHandler dispatcher;
    private AVService avService;
    private StockPriceService spService;

    @Autowired
    public void setDispatcher(WebSocketHandler dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Autowired
    public void setAVService(AVService service) {
        this.avService = service;
    }

    @Autowired
    public void setSPService(StockPriceService service) {
        this.spService = service;
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void getUpdatedStockInfo() {

        if(lastUpdate == null) {
            logger.info("First run. Retrieving latest info and storing it");
            IntradayStocksInfo stocksInfo = avService.getStockInfo();
            for (StockPriceInfo info : stocksInfo.getTimeSeries()) {
                spService.addEntry(info, "IBM");
            }
            return;
        }

        logger.info("Retrieving stock info");
        StockPriceInfo infoUpdate = avService.getLatestStockInfo();

        if (!infoUpdate.getUpdateDate().equals(lastUpdate)) {
            logger.info("Detected update. Sending to clients");
            dispatcher.sendStocksUpdateToAll(infoUpdate);
            spService.addEntry(infoUpdate, "IBM");
            lastUpdate = infoUpdate.getUpdateDate();
        } else {
            logger.info("No updates detected.");
        }
    }

}
