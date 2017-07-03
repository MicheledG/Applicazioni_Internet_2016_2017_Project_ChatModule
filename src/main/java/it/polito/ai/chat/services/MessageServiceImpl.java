package it.polito.ai.chat.services;

import it.polito.ai.chat.exception.CustomNotFoundException;
import it.polito.ai.chat.exception.UnknownTopic;
import it.polito.ai.chat.model.messages.BikeTripMessage;
import it.polito.ai.chat.model.messages.BusMetroMessage;
import it.polito.ai.chat.model.messages.StoredMessage;
import it.polito.ai.chat.model.messages.TrafficMessage;
import it.polito.ai.chat.repositories.messages.BikeTripMessageRepository;
import it.polito.ai.chat.repositories.messages.BusMetroMessageRepository;
import it.polito.ai.chat.repositories.messages.TrafficMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    /**
     * Persist Message on MongoDB
     *
     * @param topic
     * @param message
     */
    @Override
    public void persistMessage(String topic, StoredMessage message) {
        // select the right repository where to store the message depending on the topic
        switch (topic) {
            case BUS_METRO:
                busMetroMessageRepository.save(new BusMetroMessage(message.getTimestamp(), message.getUsername(), message.getContent()));
                break;

            case TRAFFIC:
                trafficMessageRepository.save(new TrafficMessage(message.getTimestamp(), message.getUsername(), message.getContent()));
                break;

            case BIKE_TRIP:
                bikeTripMessageRepository.save(new BikeTripMessage(message.getTimestamp(), message.getUsername(), message.getContent()));
                break;
        }
    }

    /**
     * For DEBUG Purpose, create count messages for selected topic on MongoDB
     *
     * @param topicName
     * @param count
     * @return
     * @throws UnknownTopic
     */
    @Override
    public String createCountMessages(String topicName, Integer count) throws UnknownTopic {
        switch (topicName) {
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
                throw new UnknownTopic("Cannot create messages about this topic: " + topicName);
        }
    }

    @Override
    public Object getTopicMessages(String topicName, PageRequest pageRequest) {
        switch (topicName) {
            case BUS_METRO:
                return busMetroMessageRepository.findAll(pageRequest).getContent();

            case TRAFFIC:
                return trafficMessageRepository.findAll(pageRequest).getContent();

            case BIKE_TRIP:
                return bikeTripMessageRepository.findAll(pageRequest).getContent();

            default:
                return new CustomNotFoundException("Topic " + topicName + " doesn't exist").getMessage();
        }
    }

}
