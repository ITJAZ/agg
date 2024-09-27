package io.almonds.agg.kafka.serialization;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeDeserializer implements Deserializer<JsonNode> {

  @Override
  public JsonNode deserialize(String topic, byte[] data) {
    try {
      return DefaultJsonSerde.OBJECT_MAPPER.readTree(data);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
