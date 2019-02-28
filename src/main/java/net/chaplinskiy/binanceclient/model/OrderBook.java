package net.chaplinskiy.binanceclient.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderBook {
    private Long lastUpdateId;
    List<Order> bids;
    List<Order> asks;
}
