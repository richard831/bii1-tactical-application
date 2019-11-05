package com.tactical.repository;
import com.tactical.domain.BII;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the BII entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BIIRepository extends MongoRepository<BII, String> {

}
