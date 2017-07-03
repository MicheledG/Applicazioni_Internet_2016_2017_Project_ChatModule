package it.polito.ai.chat.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import it.polito.ai.chat.model.messages.BikeTripMessage;
import it.polito.ai.chat.model.messages.BusMetroMessage;
import it.polito.ai.chat.model.messages.ForwardedMessage;
import it.polito.ai.chat.model.messages.StoredMessage;
import it.polito.ai.chat.model.messages.TrafficMessage;
import it.polito.ai.chat.repositories.messages.BikeTripMessageRepository;
import it.polito.ai.chat.repositories.messages.BusMetroMessageRepository;
import it.polito.ai.chat.repositories.messages.TrafficMessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	
	private static final String BUS_METRO = "BusMetro";
    private static final String TRAFFIC = "Traffic";
    private static final String BIKE_TRIP = "BikeTrip";
	
	@Autowired
    private BikeTripMessageRepository bikeTripMessageRepository;
    @Autowired
    private TrafficMessageRepository trafficMessageRepository;
    @Autowired
    private BusMetroMessageRepository busMetroMessageRepository;
    
    @Override
    public void persistMessage(String topic, StoredMessage message) {
        
        // select the right repository where to store the message depending on the topic
        switch (topic) {
        case BUS_METRO:
            for (int i = 0; i < count; i++) {
                busMetroMessageRepository.save(new BusMetroMessage(new Date(), "mail1@test.com", "Bus&Metro " + i));
            }
            return "Created " + count + " messages about topic: " + BUS_METRO;

        case TRAFFIC:
            for (int i = 0; i < count; i++) {
                trafficMessageRepository.save(new TrafficMessage(new Date(), "mail1@test.com", "Bus&Traffic " + i));
            }
            return "Created " + count + " messages about topic: " + TRAFFIC;

        case BIKE_TRIP:
            for (int i = 0; i < count; i++) {
                bikeTripMessageRepository.save(new BikeTripMessage(new Date(), "mail1@test.com", "BikeTrip " + i));
            }
            return "Created " + count + " messages about topic: " + BIKE_TRIP;

        default:
            return "Cannot create messages about this topic: " + topicName;
    }
        
        
        mongoOperation.insert(mongoMessage, topic);
    }

}
