package io.almonds.agg.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document("bk")
@Getter
@Setter
public class BK {

  @Id
  private long id;

  private String blno;

  private List<String> cntrNo;
}
