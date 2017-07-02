package it.polito.ai.chatmodule.repositories.messages;

import it.polito.ai.chatmodule.model.messages.BusMetroMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by france193 on 24/06/2017.
 */
@Repository
public interface BusMetroMessageRepository extends MongoRepository<BusMetroMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<BusMetroMessage> findBusMetroMessages(Pageable pageable);
}
