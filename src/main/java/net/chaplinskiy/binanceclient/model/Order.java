package net.chaplinskiy.binanceclient.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonSerialize(using = OrderSerializer.class)
@JsonDeserialize(using = OrderDeserializer.class)
public class Order {
    private BigDecimal price;
    private BigDecimal quantity;
}
