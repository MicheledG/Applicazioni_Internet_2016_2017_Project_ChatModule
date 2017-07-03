package it.polito.ai.chat.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.polito.ai.chat.model.messages.BikeTripMessage;

/**
 * Created by france193 on 24/06/2017.
 */
@Repository
public interface BikeTripMessageRepository extends MongoRepository<BikeTripMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BikeTripMessage> findBikeTripMessages(Pageable pageable);
}
