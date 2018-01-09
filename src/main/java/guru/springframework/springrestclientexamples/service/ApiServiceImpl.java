package guru.springframework.springrestclientexamples.service;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;
    private final String API_URI;

    public ApiServiceImpl(RestTemplate restTemplate, final @Value("${api.uri}") String apiUri) {
        this.restTemplate = restTemplate;
        API_URI = apiUri;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(API_URI)
                .queryParam("limit", limit);

        UserData userData = restTemplate.getForObject(builder.toUriString(), UserData.class);
        return userData.getData();
    }
}
