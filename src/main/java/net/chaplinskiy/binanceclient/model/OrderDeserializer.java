package net.chaplinskiy.binanceclient.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

public class OrderDeserializer extends JsonDeserializer<Order> {

    @Override
    public Order deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode jsonNode = codec.readTree(jsonParser);

        String price = jsonNode.get(0).asText();
        String quantity = jsonNode.get(1).asText();

        Order order = new Order();
        order.setPrice(new BigDecimal(price));
        order.setQuantity(new BigDecimal(quantity));
        return order;
    }
}
