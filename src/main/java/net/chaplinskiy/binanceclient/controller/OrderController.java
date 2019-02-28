package net.chaplinskiy.binanceclient.controller;

import net.chaplinskiy.binanceclient.dto.OrderResult;
import net.chaplinskiy.binanceclient.service.BinanceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/v1/order")
@RestController
public class OrderController {

    @Autowired
    BinanceClient binanceClient;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OrderResult> getQuantity(@RequestParam String symbol, @RequestParam double amountOfMoney) {
        OrderResult result = binanceClient.getQuantity(symbol, amountOfMoney);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
