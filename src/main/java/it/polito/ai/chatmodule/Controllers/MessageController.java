package it.polito.ai.chatmodule.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ai.chatmodule.ChatMessages.Model.BikeTripMessage;
import it.polito.ai.chatmodule.ChatMessages.Model.BusMetroMessage;
import it.polito.ai.chatmodule.ChatMessages.Model.TrafficMessage;
import it.polito.ai.chatmodule.ChatMessages.Repositories.BikeTripMessageRepository;
import it.polito.ai.chatmodule.ChatMessages.Repositories.BusMetroMessageRepository;
import it.polito.ai.chatmodule.ChatMessages.Repositories.TrafficMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by france193 on 24/06/2017.
 */
@RestController
public class MessageController {
    private static final String BUS_METRO = "BusMetro";
    private static final String TRAFFIC = "Traffic";
    private static final String BIKE_TRIP = "BikeTrip";

    @Autowired
    private BusMetroMessageRepository busMetroMessageRepository;
    @Autowired
    private BikeTripMessageRepository bikeTripMessageRepository;
    @Autowired
    private TrafficMessageRepository trafficMessageRepository;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String getMessages(@RequestParam(value = "topic") String topic,
                              @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        PageRequest pageRequest = new PageRequest(0,
                count,
                new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp")));

        switch (topic) {
            case BUS_METRO:
                Page<BusMetroMessage> msgs1 = busMetroMessageRepository.findAll(pageRequest);
                return mapper.writeValueAsString(msgs1.getContent());

            case TRAFFIC:
                Page<TrafficMessage> msgs2 = trafficMessageRepository.findAll(pageRequest);
                return mapper.writeValueAsString(msgs2.getContent());

            case BIKE_TRIP:
                Page<BikeTripMessage> msgs3 = bikeTripMessageRepository.findAll(pageRequest);
                return mapper.writeValueAsString(msgs3.getContent());

            default:
                return "Cannot retrieve messages about this topic: " + topic;
        }
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String createMessages(@RequestParam(value = "topic") String topic,
                                 @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {
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
                return "Cannot create messages about this topic: " + topic;
        }
    }
}