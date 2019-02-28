package net.chaplinskiy.binanceclient.service;

import net.chaplinskiy.binanceclient.dto.OrderResult;
import net.chaplinskiy.binanceclient.exception.BadGatewayException;
import net.chaplinskiy.binanceclient.model.Order;
import net.chaplinskiy.binanceclient.model.OrderBook;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class BinanceClient {
    private static final String Binance_URI = "https://api.binance.com/api/v1/depth";

    private RestTemplate restTemplate = new RestTemplate();

    public OrderResult getQuantity(String symbol, double amountOfMoney) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(Binance_URI)
                .queryParam("symbol", symbol);


        BigDecimal amount = new BigDecimal(amountOfMoney);
        BigDecimal totalBidsMoney = new BigDecimal(0);
        BigDecimal totalQuantityToken = new BigDecimal(0);
        OrderResult orderResult = new OrderResult();

        OrderBook forObject = restTemplate.getForObject(builder.toUriString(), OrderBook.class);
        List<Order> bids = forObject.getBids();

        for (int i = 0; i < bids.size(); i++) {
            BigDecimal price = bids.get(i).getPrice();
            BigDecimal quantity = bids.get(i).getQuantity();

            if (amount.compareTo(price) == -1){
                totalQuantityToken = amount.divide(price, 8, RoundingMode.HALF_UP);
                break;
            }

            BigDecimal sumMoneyBid = price.multiply(quantity);
            totalBidsMoney = totalBidsMoney.add(sumMoneyBid);
            if (amount.compareTo(totalBidsMoney) == -1){
                totalBidsMoney = totalBidsMoney.subtract(sumMoneyBid);
                BigDecimal restMoney = amount.subtract(totalBidsMoney);
                BigDecimal bidsToken = restMoney.divide(price, 8, RoundingMode.HALF_UP);
                totalQuantityToken = totalQuantityToken.add(bidsToken);
                totalBidsMoney = totalBidsMoney.add(restMoney);
                break;
            }
            totalQuantityToken = totalQuantityToken.add(quantity);
        }

        if (totalBidsMoney.compareTo(amount) == -1 && (totalBidsMoney.compareTo(BigDecimal.valueOf(0)) != 0)){
            throw new BadGatewayException();
        }

        orderResult.setResult(totalQuantityToken);
        orderResult.setToken(symbol);
        return orderResult;
    }
}
