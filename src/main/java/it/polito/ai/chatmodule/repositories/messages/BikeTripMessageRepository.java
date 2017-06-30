package it.polito.ai.chatmodule.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.ai.chatmodule.model.messages.BikeTripMessage;

/**
 * Created by france193 on 24/06/2017.
 */
public interface BikeTripMessageRepository extends MongoRepository<BikeTripMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BikeTripMessage> findBikeTripMessages(Pageable pageable);
}
