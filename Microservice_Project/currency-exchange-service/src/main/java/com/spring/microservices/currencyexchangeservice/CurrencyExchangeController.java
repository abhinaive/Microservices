package com.spring.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeRate(@PathVariable String from, @PathVariable String to){

    //  CurrencyExchange currencyExchange = new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(50));
        String port = environment.getProperty("local.server.port");

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from,to);

        if(currencyExchange == null){
            throw new RuntimeException("Unable to find Data for " + from + "to " + to);
        }

        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
