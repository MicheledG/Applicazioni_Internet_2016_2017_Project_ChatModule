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
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by france193 on 24/06/2017.
 */
@RestController
public class MessageController {
    private static final String topic1 = "BusMetro";
    private static final String topic2 = "Traffic";
    private static final String topic3 = "BikeTrip";

    @Autowired
    private BusMetroMessageRepository busMetroMessageRepository;
    @Autowired
    private BikeTripMessageRepository bikeTripMessageRepository;
    @Autowired
    private TrafficMessageRepository trafficMessageRepository;

    @RequestMapping("/messages")
    public String getMessages(@RequestParam(value = "topic") String topic,
                              @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        switch (topic) {
            case topic1:
                List<BusMetroMessage> msgs1 = busMetroMessageRepository.findBusMetroMessages(new PageRequest(0, count));
                return mapper.writeValueAsString(msgs1);

            case topic2:
                List<TrafficMessage> msgs2 = trafficMessageRepository.findTrafficMessages(new PageRequest(0, count));
                return mapper.writeValueAsString(msgs2);

            case topic3:
                List<BikeTripMessage> msgs3 = bikeTripMessageRepository.findBikeTripMessages(new PageRequest(0, count));
                return mapper.writeValueAsString(msgs3);

            default:
                break;
        }

        return "Error";
    }

    @RequestMapping("/createMessages")
    public void createMessages(@RequestParam(value = "topic") String topic,
                               @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) throws JsonProcessingException {
        switch (topic) {
            case topic1:
                for (int i = 0; i < count; i++) {
                    busMetroMessageRepository.save(new BusMetroMessage(new Date(), "mail1@test.com", "Bus&Metro " + i));
                }
                break;

            case topic2:
                for (int i = 0; i < count; i++) {
                    trafficMessageRepository.save(new TrafficMessage(new Date(), "mail1@test.com", "Bus&Traffic " + i));
                }
                break;

            case topic3:
                for (int i = 0; i < count; i++) {
                    bikeTripMessageRepository.save(new BikeTripMessage(new Date(), "mail1@test.com", "BikeTrip " + i));
                }
                break;

            default:
                break;
        }
    }
}