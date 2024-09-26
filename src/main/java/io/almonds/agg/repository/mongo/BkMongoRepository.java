package io.almonds.agg.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.almonds.agg.model.BK;

@Repository
public interface BkMongoRepository extends MongoRepository<BK, Long> {

}
