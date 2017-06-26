package it.polito.ai.chatmodule.ChatMessages.Repositories;

import it.polito.ai.chatmodule.ChatMessages.Model.BikeTripMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by france193 on 24/06/2017.
 */
public interface BikeTripMessageRepository extends MongoRepository<BikeTripMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BikeTripMessage> findBikeTripMessages(Pageable pageable);
}
