package io.almonds.agg.kafka.serialization;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeDeserializer implements Deserializer<JsonNode> {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public JsonNode deserialize(String topic, byte[] data) {
    try {
      return OBJECT_MAPPER.readTree(data);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
