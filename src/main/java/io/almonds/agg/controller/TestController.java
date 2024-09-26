package io.almonds.agg.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.almonds.agg.model.BK;
import io.almonds.agg.repository.mongo.BkMongoRepository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
public class TestController {

  @Autowired
  private JedisPool jedisPool;

  @Autowired
  private BkMongoRepository bkMongoRepository;

  @GetMapping("/hi")
  public BK create() {
    BK bk = new BK();
    try (Jedis jedis = jedisPool.getResource()) {
      String blno = "%012d".formatted(jedis.incr("blno"));
      bk.setBlno(blno);
      double cntrNo = jedis.zincrby("cntrNo", 1, blno);
      bk.setCntrNo(Arrays.asList(cntrNo + ""));

      bkMongoRepository.save(bk);

      return bk;
    }
  }
}
