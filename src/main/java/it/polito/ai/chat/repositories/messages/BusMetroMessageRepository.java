package it.polito.ai.chat.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.polito.ai.chat.model.messages.BusMetroMessage;

/**
 * Created by france193 on 24/06/2017.
 */
@Repository
public interface BusMetroMessageRepository extends MongoRepository<BusMetroMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BusMetroMessage> findBusMetroMessages(Pageable pageable);
}
