package net.chaplinskiy.binanceclient.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderBook {
    private Long lastUpdateId;
    private List<Order> bids;
    private List<Order> asks;
}
