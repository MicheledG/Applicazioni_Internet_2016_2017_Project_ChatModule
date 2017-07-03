package it.polito.ai.chat.services;

import org.springframework.web.client.RestTemplate;

public class RemoteProfileServiceImpl implements ProfileService {

	private final static String REMOTE_PROFILE_ENDPOINT = "http://localhost:8083/nickname";
	
	@Override
	public String getNickname(String username) {
		
		// Send a POST request to the Authentication Module
		RestTemplate restTemplate = new RestTemplate();
		String nickname;
		try {
			nickname = restTemplate.getForObject(
					REMOTE_PROFILE_ENDPOINT+"?username="+username,
					String.class);
		} catch (Exception e) {
			//TODO: retrieve of the nickname associated to the given username failed
			System.err.println("retrieve of the nickname associated to the given username failed");
			System.err.println(e.getMessage());
			return null;
		}
		
		return nickname;		
	}

}
