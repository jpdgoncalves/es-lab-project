package com.example.demo.repositories;

import com.example.demo.models.entities.StockPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockPriceRepository extends CrudRepository<StockPrice, Long> {

    List<StockPrice> findTop100ByOrderByUpdateDateDesc();
}
