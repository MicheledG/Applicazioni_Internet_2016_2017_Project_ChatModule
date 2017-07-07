package it.polito.ai.chat.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteProfileServiceImpl implements ProfileService {

    private static final String REMOTE_NICKNAME_ENDPOINT = "http://localhost:8083/profile/nickname?username=";

    @Override
    public String getNickname(String username) {

        // Define a template to perform rest requests
        RestTemplate restTemplate = new RestTemplate();

        // Get from the Profile module the nickname related to the current logged username
        String nickname = null;
        try {
            nickname = restTemplate.getForObject(REMOTE_NICKNAME_ENDPOINT + username, String.class);
        } catch (Exception e) {
            // Something goes wrong with the REST request

        }

        return nickname;
    }

}
