package com.example.demo.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class StockPrice {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String symbol;
    private String updateDate;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;

    public StockPrice(String symbol, String updateDate,
                      float open, float high, float low, float close, float volume) {
        this.symbol = symbol;
        this.updateDate = updateDate;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}
