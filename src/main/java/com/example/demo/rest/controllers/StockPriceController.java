package com.example.demo.rest.controllers;

import com.example.demo.models.rest.StockPriceInfo;
import com.example.demo.services.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stocks")
public class StockPriceController {

    private StockPriceService service;

    @Autowired
    public void setService(StockPriceService service) {
        this.service = service;
    }

    @GetMapping(value = "/intraday", produces = "application/json")
    public List<StockPriceInfo> testRedirection() {
        return service.getLatestPrices();
    }
}
