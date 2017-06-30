package it.polito.ai.chatmodule.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.ai.chatmodule.model.messages.BusMetroMessage;

/**
 * Created by france193 on 24/06/2017.
 */
public interface BusMetroMessageRepository extends MongoRepository<BusMetroMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BusMetroMessage> findBusMetroMessages(Pageable pageable);
}
