package net.chaplinskiy.binanceclient.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OrderSerializer extends JsonSerializer<Order> {
    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(order.getPrice());
        jsonGenerator.writeNumber(order.getQuantity());
        jsonGenerator.writeEndArray();
    }
}
