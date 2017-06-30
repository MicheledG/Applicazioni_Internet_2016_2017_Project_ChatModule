package it.polito.ai.chatmodule.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.ai.chatmodule.model.messages.BikeTripMessage;
import org.springframework.stereotype.Repository;

/**
 * Created by france193 on 24/06/2017.
 */
@Repository
public interface BikeTripMessageRepository extends MongoRepository<BikeTripMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BikeTripMessage> findBikeTripMessages(Pageable pageable);
}
